class Vehicle:
    def __init__(self, license_plate, vehicle_type):
        self.license_plate = license_plate
        self.vehicle_type = vehicle_type

class TwoWheeler(Vehicle):
    def __init__(self, license_plate):
        super().__init__(license_plate, "two")

class FourWheeler(Vehicle):
    def __init__(self, license_plate):
        super().__init__(license_plate, "four")