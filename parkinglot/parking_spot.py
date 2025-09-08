<<<<<<< HEAD
from vehicle import Vehicle
=======
from parkinglot.vehicle import Vehicle
>>>>>>> 06d1a22be2ecacc19efbd02e7cb968d88d995cdf
class ParkingSpot:
    def __init__(self, spot_id, size):
        self.spot_id = spot_id
        self.is_occupied = False
        self.vehicle: Vehicle = None

    def park_vehicle(self, vehicle: Vehicle) -> bool:
        if not self.is_occupied:
            self.vehicle = vehicle
            self.is_occupied = True
            return True
        return False

    def remove_vehicle(self) -> bool:
        if self.is_occupied:
            self.vehicle = None
            self.is_occupied = False
            return True
        return False

    def is_available(self) -> bool:
        return not self.is_occupied