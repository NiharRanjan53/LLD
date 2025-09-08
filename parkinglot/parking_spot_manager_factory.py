from threading import Lock
from typing import Dict, Optional
<<<<<<< HEAD
from two_wheeler_spot_manager import TwoWheelerSpotManager
from four_wheeler_spot_manager import FourWheelerSpotManager
from parking_spot_manager import ParkingSpotManager
=======
from parkinglot.two_wheeler_spot_manager import TwoWheelerSpotManager
from parkinglot.four_wheeler_spot_manager import FourWheelerSpotManager
from parkinglot.parking_spot_manager import ParkingSpotManager
>>>>>>> 06d1a22be2ecacc19efbd02e7cb968d88d995cdf

class ParkingSpotManagerFactory:
    """
    Registry-style factory: single manager instance per spot_type key.
    Keys: "two", "four" (or enums you prefer).
    """
    _registry: Dict[str, ParkingSpotManager] = {}
    _lock = Lock()

    @classmethod
    def get_parking_spot_manager(cls, spot_type: str, create_if_missing: bool = True, **kwargs) -> Optional[ParkingSpotManager]:
        """
        - spot_type: 'two' or 'four' (string or enum)
        - create_if_missing: if True, will create manager with kwargs (e.g., count).
        - kwargs are passed to the manager constructor on creation.
        """
        with cls._lock:
            if spot_type in cls._registry:
                return cls._registry[spot_type]

            if not create_if_missing:
                raise KeyError(f"No manager for spot_type={spot_type}")
<<<<<<< HEAD
    
            # create and register
            if spot_type == "TwoWheeler":
                manager = TwoWheelerSpotManager(**kwargs)
            elif spot_type == "FourWheeler":
=======

            # create and register
            if spot_type == "two":
                manager = TwoWheelerSpotManager(**kwargs)
            elif spot_type == "four":
>>>>>>> 06d1a22be2ecacc19efbd02e7cb968d88d995cdf
                manager = FourWheelerSpotManager(**kwargs)
            else:
                raise ValueError("unsupported spot type")
            cls._registry[spot_type] = manager
            return manager