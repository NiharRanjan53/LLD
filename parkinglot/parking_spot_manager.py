from typing import List, Dict, Optional
from threading import Lock
from parkinglot.vehicle import Vehicle
from parkinglot.parking_spot import ParkingSpot


class ParkingSpotManager:
    """
    Manages a collection of ParkingSpot objects of a single spot-type.
    This class is intentionally generic: specialized managers (Two/Four) can extend it.
    """

    def __init__(self):
        self.spots: List[ParkingSpot] = []
        self.spot_map: Dict[int, ParkingSpot] = {}
        self._lock = Lock()
    
    # -------- basic CRUD for spots --------
    def add_spot(self, spot: ParkingSpot) -> None:
        if spot.spot_id in self.spot_map:
            raise ValueError(f"spot {spot.spot_id} already exists")
        self.spots.append(spot)
        self.spot_map[spot.spot_id] = spot
    
    def extend_spots(self, new_spots: List[ParkingSpot]):
        """Add multiple spots safely later"""
        with self._lock:
            for spot in new_spots:
                if spot.spot_id in self.spot_map:
                    raise ValueError(f"Duplicate spot_id {spot.spot_id}")
                self.spots.append(spot)
                self.spot_map[spot.spot_id] = spot

    def remove_spot(self, spot_id: int) -> None:
        if spot_id in self.spot_map:
            spot = self.spot_map.pop(spot_id)
            self.spots.remove(spot)

    # -------- allocation / freeing --------
    def find_available_spot(self) -> Optional[ParkingSpot]:
        """
        Basic first-free linear scan. Replaceable by strategy if needed.
        """
        for spot in self.spots:
            if not spot.is_occupied:
                return spot
        return None

    def park_vehicle(self, vehicle: Vehicle) -> Optional[ParkingSpot]:
        """
        Returns the ParkingSpot if allocation succeeded, otherwise None.
        Thread-safe.
        """
        with self._lock:
            spot = self.find_available_spot()
            if not spot:
                return None
            success = spot.park_vehicle(vehicle)
            return spot if success else None

    def remove_vehicle(self, spot_id: int) -> bool:
        """
        Free the spot with spot_id. Returns True if freed, False otherwise.
        """
        with self._lock:
            if spot_id in self.spot_map:
                return self.spot_map[spot_id].remove_vehicle()
            return False

    # -------- helpers / reporting --------
    def get_total_spots(self) -> int:
        return len(self.spots)

    def get_available_count(self) -> int:
        return sum(1 for s in self.spots if not s.is_occupied)

    def get_status(self) -> Dict[str, object]:
        occupied = [s.spot_id for s in self.spots if s.is_occupied]
        free = [s.spot_id for s in self.spots if not s.is_occupied]
        return {"total": self.get_total_spots(), "available": len(free),
                "free": free, "occupied": occupied}