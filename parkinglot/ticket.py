<<<<<<< HEAD
from vehicle import Vehicle
=======
from parkinglot.vehicle import Vehicle
>>>>>>> 06d1a22be2ecacc19efbd02e7cb968d88d995cdf
class Ticket:
    def __init__(self, ticket_id: str, vehicle: Vehicle, spot_id: str, entry_time: str):
        self.ticket_id = ticket_id
        self.vehicle = vehicle
<<<<<<< HEAD
        self.vehicle_type = vehicle.vehicle_type
        self.spot_id = spot_id
        self.entry_time = entry_time
        self.exit_time = None
        self.parking_duration = None
        self.cost = None
        self.paid = False

    def close_ticket(self, exit_time: str, parking_duration: float = None, cost: float = None):
        self.exit_time = exit_time
        self.parking_duration = parking_duration
        self.cost = cost
=======
        self.spot_id = spot_id
        self.entry_time = entry_time
        self.exit_time = None

    def close_ticket(self, exit_time: str):
        self.exit_time = exit_time
>>>>>>> 06d1a22be2ecacc19efbd02e7cb968d88d995cdf

    def get_ticket_details(self):
        return {
            "ticket_id": self.ticket_id,
            "vehicle_id": self.vehicle.license_plate,
<<<<<<< HEAD
            "vehicle_type": self.vehicle_type,
            "spot_id": self.spot_id,
            "entry_time": self.entry_time,
            "exit_time": self.exit_time,
            "parking_duration": self.parking_duration,
            "cost": self.cost
=======
            "spot_id": self.spot_id,
            "entry_time": self.entry_time,
            "exit_time": self.exit_time
>>>>>>> 06d1a22be2ecacc19efbd02e7cb968d88d995cdf
        }