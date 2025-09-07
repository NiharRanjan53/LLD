from typing import Optional, List
from datetime import datetime
from parking_spot_manager_factory import ParkingSpotManagerFactory
from parking_spot import ParkingSpot
from vehicle import Vehicle
from parking_spot_manager import ParkingSpotManager

from ticket import Ticket
class EntranceGate:
    def __init__(self, gate_id):
        self.gate_id = gate_id
        self.parking_spot_manager_factory: ParkingSpotManagerFactory = ParkingSpotManagerFactory()
        self.parking_spot_manager = None
        self.ticket: Ticket = None
        self.is_open = False
        print("Entrance gate created with ID:", gate_id)


    def get_parking_spot_manager(self, vehicle_type):
        self.parking_spot_manager = ParkingSpotManagerFactory.get_parking_spot_manager(vehicle_type)
        return self.parking_spot_manager

    def find_parking_spot(self, vehicle_type: str) -> Optional[ParkingSpot]:
        self.parking_spot_manager = ParkingSpotManagerFactory.get_parking_spot_manager(vehicle_type)
        return self.parking_spot_manager.find_available_spot()

    def book_parking_spot(self, vehicle: Vehicle) -> Ticket:
        spot = self.parking_spot_manager.park_vehicle(vehicle)
        if not spot:
            return None
        current_time = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        self.is_open = True
        self.ticket = Ticket(ticket_id=f"TICKET-{spot.spot_id}-{vehicle.license_plate}", vehicle=vehicle, spot_id=spot.spot_id, entry_time=current_time)
        self.is_open = False
        return self.ticket

    def get_entrance_gate_details(self):
        return {
            "gate_id": self.gate_id,
            "is_open": self.is_open,
            "parking_spot_manager": self.parking_spot_manager
        }
