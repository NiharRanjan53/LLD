from two_wheeler_cost_computation import TwoWheelerCostComputation
from four_wheeler_cost_computation import FourWheelerCostComputation
from cost_computation import CostComputation
from pricing_startegy import PricingStrategy

class CostComputationFactory:
    @staticmethod
    def get_cost_computation(vehicle_type: str, pricing_strategy: PricingStrategy) -> CostComputation:
        if vehicle_type == "TwoWheeler":
            return TwoWheelerCostComputation(pricing_strategy)
        elif vehicle_type == "FourWheeler":
            return FourWheelerCostComputation(pricing_strategy)
        else:
            raise ValueError("Invalid vehicle type")