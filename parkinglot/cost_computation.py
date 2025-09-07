
from abc import ABC, abstractmethod
from pricing_startegy import PricingStrategy

class CostComputation(ABC):
    """
    Abstract base class for cost computation strategies.
    Subclasses must implement compute_cost and generate_bill methods.
    """
    def __init__(self, pricing_strategy: PricingStrategy):
        self.pricing_strategy = pricing_strategy

    @abstractmethod
    def compute_cost(self, ticket):
        pass

    @abstractmethod
    def generate_bill(self, ticket):
        pass