from abc import ABC, abstractmethod
class PricingStrategy(ABC):
    @abstractmethod
    def calculate_fee(self, duration_in_seconds: float) -> float:
        pass
