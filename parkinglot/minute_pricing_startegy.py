from pricing_startegy import PricingStrategy
class MinuteWisePricingStrategy(PricingStrategy):
    def __init__(self, rate_per_minute: float, min_minutes: int = 1):
        self.rate_per_minute = rate_per_minute
        self.min_minutes = min_minutes

    def calculate_fee(self, duration_in_seconds: float) -> float:
        duration_in_minutes = duration_in_seconds / 60
        if duration_in_minutes < self.min_minutes:
            duration_in_minutes = self.min_minutes  
        return duration_in_minutes * self.rate_per_minute

    def generate_bill(self, ticket):
        fee = self.calculate_fee(ticket)
        bill = {
            "ticket_id": ticket.ticket_id,
            "vehicle_id": ticket.vehicle.vehicle_id,
            "spot_id": ticket.spot_id,
            "entry_time": ticket.entry_time,
            "exit_time": ticket.exit_time,
            "total_fee": fee
        }
        duration = (ticket.exit_time - ticket.entry_time).total_seconds() / 60  # duration in minutes
        rate_per_minute = self.pricing_details.get(ticket.vehicle.vehicle_type, 0)
        return duration * rate_per_minute

    def generate_bill(self, ticket):
        fee = self.calculate_fee(ticket)
        bill = {
            "ticket_id": ticket.ticket_id,
            "vehicle_id": ticket.vehicle.vehicle_id,
            "spot_id": ticket.spot_id,
            "entry_time": ticket.entry_time,
            "exit_time": ticket.exit_time,
            "total_fee": fee
        }
        return bill