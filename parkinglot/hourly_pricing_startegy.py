from pricing_startegy import PricingStrategy

class HourWisePricingStrategy(PricingStrategy):
    def __init__(self, rate_per_hour: float, minimum_hours: int = 1):
        self.rate_per_hour = rate_per_hour
        self.minimum_hours = minimum_hours

    def calculate_fee(self, duration_in_seconds: float) -> float:
        duration = duration_in_seconds / 3600  # duration in hours
        if duration < self.minimum_hours:
            duration = self.minimum_hours
        return duration * self.rate_per_hour

    def generate_bill(self, ticket):
        fee = self.calculate_fee(ticket)
        return {
            "ticket_id": ticket.ticket_id,
            "vehicle_id": ticket.vehicle.vehicle_id,
            "amount_due": fee
        }