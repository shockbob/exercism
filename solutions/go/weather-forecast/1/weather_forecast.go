
// Package weather provides tools to get 
// weather information.
package weather

var (
    // CurrentCondition store stuff for a while.
	CurrentCondition string
    // CurrentLocation store more stuff for a while.
	CurrentLocation  string
)
// Forecast returns a readable forecast 
// given a city and condition.
func Forecast(city, condition string) string {
	CurrentLocation, CurrentCondition = city, condition
	return CurrentLocation + " - current weather condition: " + CurrentCondition
}
