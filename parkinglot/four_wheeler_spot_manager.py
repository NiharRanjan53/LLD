from parking_spot_manager import ParkingSpotManager
from four_wheeler_spot import FourWheelerSpot
class FourWheelerSpotManager(ParkingSpotManager):
    """
    Specialized manager for FourWheeler spots.
    For now it inherits behavior from ParkingSpotManager, but we allow custom logic here.
    """

    def __init__(self, count: int = 10):
        super().__init__()
        self.add_parking_spots(count)
        print(f"Initialized FourWheelerSpotManager with {count} spots.")

    def add_parking_spots(self, count: int, start_id: int = None):
        """
        Create new spots with unique IDs.
        If start_id is given, use it to continue numbering.
        """
        if start_id is None and self.spots:
            # continue after last ID
            start_id = max(self.spot_map.keys()) + 1
        elif start_id is None:
            start_id = 1

        new_spots = [FourWheelerSpot(i) for i in range(start_id, start_id + count)]
        self.extend_spots(new_spots)