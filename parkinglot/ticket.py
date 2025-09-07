from parkinglot.vehicle import Vehicle
class Ticket:
    def __init__(self, ticket_id: str, vehicle: Vehicle, spot_id: str, entry_time: str):
        self.ticket_id = ticket_id
        self.vehicle = vehicle
        self.spot_id = spot_id
        self.entry_time = entry_time
        self.exit_time = None

    def close_ticket(self, exit_time: str):
        self.exit_time = exit_time

    def get_ticket_details(self):
        return {
            "ticket_id": self.ticket_id,
            "vehicle_id": self.vehicle.license_plate,
            "spot_id": self.spot_id,
            "entry_time": self.entry_time,
            "exit_time": self.exit_time
        }