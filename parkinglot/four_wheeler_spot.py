<<<<<<< HEAD
from parking_spot import ParkingSpot
=======
from parkinglot.parking_spot import ParkingSpot
>>>>>>> 06d1a22be2ecacc19efbd02e7cb968d88d995cdf
class FourWheelerSpot(ParkingSpot):
    def __init__(self, spot_id):
        super().__init__(spot_id, "FourWheeler")