import java.util.ArrayList;

//public antes de interface
interface Subject{
	public void registerObserver(Observer o);
	public void remove(Observer o);
	public void notifyObservers();
}
interface Observer{
	public void update(float temp, float humidity, float pressure);
}
interface DisplayElemnt{
	public void display();
}

//public antes de class
class WeatherData implements Subject{
	private ArrayList observers;
	private float temperature;
	private float humidity;
	private float pressure;
	
	//contructor
	public WeatherData(){
		observers = new ArrayList();
	}
	public void registerObserver(Observer o){
		observers.add(o);
	}
	public void remove(Observer o){
		int i = observers.indexOf(o);
		if (i >= 0){
			observers.remove(i);
		}
	}
	public void notifyObservers(){
		for (int i = 0; i < observers.size(); i++){
			Observer observer = (Observer) observers.get(i);
			observer.update(temperature, humidity, pressure);
		}
	}
	public void measurementsChanged(){
		notifyObservers();
	}
	public void setMeasurements(float temperature, float humidity, float pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}
}

//public antes de class
class CurrentConditionsDisplay implements Observer, DisplayElemnt{
	private float temperature;
	private float humidity;
	private Subject weatherData;
	
	public CurrentConditionsDisplay(Subject weatherData){
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}
	public void update(float temperature, float humidity, float pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		display();
	}
	public void display(){
		System.out.println("Current conditions: " + temperature+ "F degrees and " + humidity + "% humidity");
	}
}

//public antes de class
class ForeCastDisplay implements Observer, DisplayElemnt{
	private float currentPressure = 29.92f; //f
	private float lastPressure;
	private WeatherData weatherData;
	
	public ForeCastDisplay (WeatherData weatherData){
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}
	
	public void update(float temperature, float humidity, float pressure){
		lastPressure = currentPressure;
		currentPressure = pressure;
		display();
	}
	public void display(){
		System.out.println("Forecast: ");
		if (currentPressure > lastPressure){
			System.out.println("Improving weather on the way!");
		}else if(currentPressure == lastPressure){
			System.out.println("More of the same.");
		}else if(currentPressure < lastPressure){
			System.out.println("Watch out for cooler, rainy weather.");
		}
	}
}

//public antes de class
class StatisticsDisplay implements Observer, DisplayElemnt{
	private float maxTemp = 0.0f; //f
	private float minTemp = 200.0f;
	private float tempSum = 0.0f; //f
	private int numReading;
	private WeatherData weatherData;
	
	public StatisticsDisplay(WeatherData weatherData){
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}
	public void update(float temp, float humidity, float pressure){
		tempSum += temp;
		numReading++;
		
		if (temp > maxTemp){
			maxTemp = temp;
		}
		if (temp < minTemp){
			minTemp = temp;
		}
		display();
	}
	public void display(){
		System.out.println("Avg/Max/Min temperature " + 
		(tempSum/numReading) + "/" + maxTemp + "/" + minTemp);
	}
}

public class Main{
	public static void main(String[] args){
		WeatherData weatherData = new WeatherData();
		
		CurrentConditionsDisplay currentDisplay =  
		new CurrentConditionsDisplay(weatherData);
		
		StatisticsDisplay statisticDisplay = new StatisticsDisplay(weatherData);
		
		ForeCastDisplay foreCastDisplay = new ForeCastDisplay(weatherData);
		
		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
	}
}
