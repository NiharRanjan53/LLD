class Vehicle:
    def __init__(self, license_plate, vehicle_type):
        self.license_plate = license_plate
        self.vehicle_type = vehicle_type

class TwoWheeler(Vehicle):
    def __init__(self, license_plate):
<<<<<<< HEAD
        super().__init__(license_plate, "TwoWheeler")

class FourWheeler(Vehicle):
    def __init__(self, license_plate):
        super().__init__(license_plate, "FourWheeler")
=======
        super().__init__(license_plate, "two")

class FourWheeler(Vehicle):
    def __init__(self, license_plate):
        super().__init__(license_plate, "four")
>>>>>>> 06d1a22be2ecacc19efbd02e7cb968d88d995cdf
