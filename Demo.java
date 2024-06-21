import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
class WaterLevelObserver extends JFrame{
	WaterLevelObserver(String title){
		super(title);
		setSize(300,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
	}
	public void update(int waterLevel){
		//
	}
}
class Alarm extends WaterLevelObserver{
	private JLabel lblAlarm;
	Alarm(){
		super("Alarm window");
		lblAlarm=new JLabel("");
		lblAlarm.setFont(new Font("",1,25));
		add(lblAlarm);
		setVisible(true);
	}
	public void update(int waterLevel){
		lblAlarm.setText(waterLevel>=50 ? "ON":"OFF");
	}
}
	
class WaterLevelObservable{
	private int waterLevel;
	
	private ArrayList<WaterLevelObserver>observerList=new ArrayList<>();
	
	public void addWaterLevelObserver(WaterLevelObserver ob){
		observerList.add(ob);
	}	
	
	public void setWaterLevel(int waterLevel){
		if(this.waterLevel!=waterLevel){
			this.waterLevel=waterLevel;
			notifyObject();
		}
	}
	public void notifyObject(){
		for(WaterLevelObserver ob : observerList){
			ob.update(waterLevel);
		}
	}
	
}
class WaterTank extends JFrame{
	private JSlider waterLevelSlider;
	private WaterLevelObservable waterLevelObservable;
	
	WaterTank(WaterLevelObservable waterLevelObservable){
		this.waterLevelObservable=waterLevelObservable;
		setSize(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		
		waterLevelSlider=new JSlider(JSlider.VERTICAL,0,100,50);//JSlider(int orientation, int min, int max, int value)
		waterLevelSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent evt){
				int waterLevel=waterLevelSlider.getValue();
				waterLevelObservable.setWaterLevel(waterLevel);
			}
		});
		add(waterLevelSlider);	
				
	}
}
class Demo{
	public static void main(String args[]){
		WaterLevelObservable waterLevelObservable=new WaterLevelObservable();
		waterLevelObservable.addWaterLevelObserver(new Alarm());
		//conRoom.addWaterLevelObserver(new Display());
		//conRoom.addWaterLevelObserver(new SMSSender());
		//conRoom.addWaterLevelObserver(new Splitter());
		new WaterTank(waterLevelObservable).setVisible(true);
		
	}
}
