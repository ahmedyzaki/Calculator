package Calculator;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Design extends Application
{
	static Button Seven;static Button Eight;static Button Nine;static Button Div;
	static Button Del;static Button Clear;static Button Four;static Button Five;
	static Button Six;static Button Mul;static Button LArr;static Button RArr;
	static Button One;static Button Two;static Button Three;static Button Sub;
	static Button Base;static Button Sqrt;static Button Zero;static Button Dot;
	static Button Mod;static Button Add;static Button Equal;static Button Ans;
	static double WidthListner=0,HeightListner=0;
	private double xOffset = 0; private double yOffset = 0 ;
	String Anwser="0.0";
	static boolean Start=false; public static String Results="";
	@Override
	public void start(Stage PStage) throws Exception
	{
		//To make node in fixed size in all screen
		Rectangle2D rect=Screen.getPrimary().getVisualBounds();
		WidthListner=rect.getWidth();
		HeightListner=rect.getHeight();
		//Create BorderPane Body 
		BorderPane Body=new BorderPane();
		BorderPane Header=new BorderPane();
		BorderPane Info=new BorderPane();
		BorderPane Buttons=new BorderPane();
		Header.setPrefHeight(150);
		Header.setPrefWidth(350);
		Header.setPadding(new Insets(5,5,5,5));
		Header.setStyle("-fx-background-color:#424242;"
				+ "-fx-background-radius:17px 17px 0px 0px ; ");
		Body.setTop(Header);
		Header.setTop(Info);
		//Create info bar
		HBox InfoBar=new HBox();
		Button Close=new Button();
		Button Min=new Button();
		Button Max=new Button();
		InfoBar.getChildren().addAll(Min,Max,Close);
		InfoBar.setPrefWidth(80);
		Min.setPrefHeight(20);
		Min.setPrefWidth(20);
		Min.setId("Min");
		Max.setPrefHeight(20);
		Max.setPrefWidth(20);
		Max.setId("Max");
		Close.setPrefHeight(20);
		Close.setPrefWidth(20);
		Close.setId("Close");
		Info.setRight(InfoBar);
		Label ProName=new Label("Calculator");
	 	ProName.setId("ProName");
		Info.setCenter(ProName);
		Image Icon=new Image("Calculator.png");
		ImageView imageView = new ImageView(Icon);
		Info.setLeft(imageView);
		//Create Footer
		HBox Footer=new HBox();
		Footer.setStyle("-fx-background-color:#424242;"
				+ "-fx-background-radius:0px 0px 17px 17px;");
		Label Pro=new Label("");
		Pro.setVisible(false);
		Footer.getChildren().add(Pro);
		Body.setBottom(Footer);
		//Create Screen to Display output in Header
		TextField Screen=new TextField();
		Screen.setPrefHeight(80);
		Screen.setPrefWidth(320);
		Header.setCenter(Screen);
		//Create Buttons in Center of Body
		Body.setCenter(Buttons);
		Buttons.setPadding(new Insets(7,10,10,10));
		Buttons.setCenter(Buttons());
		Equal.setId("Equal");
		//Create Scene and Stage
		Scene scene=new Scene(Body,450,500,Color.TRANSPARENT);
	    PStage.initStyle(StageStyle.TRANSPARENT);
	    PStage.initStyle(StageStyle.UNDECORATED);
		scene.getStylesheets().add("CalculatorStyle.css");
		PStage.getIcons().add(Icon);
		PStage.setScene(scene);
		PStage.show();
		//Button Actions
		Close.setOnMouseEntered(e->
		{
			Close.setStyle("-fx-background-radius:15px 15px 15px 15px;");
			Close.setPrefHeight(30);
			Close.setPrefWidth(30);
		});
		Close.setOnMouseExited(e->
		{
			Close.setPrefHeight(20);
			Close.setPrefWidth(20);
		});
		Max.setOnMouseEntered(e->
		{
			Max.setStyle("-fx-background-radius:15px 15px 15px 15px;");
			Max.setPrefHeight(30);
			Max.setPrefWidth(30);
		});
		Max.setOnMouseExited(e->
		{
			Max.setPrefHeight(20);
			Max.setPrefWidth(20);
		});
		Min.setOnMouseEntered(e->
		{
			Min.setStyle("-fx-background-radius:15px 15px 15px 15px;");
			Min.setPrefHeight(30);
			Min.setPrefWidth(30);
		});
		Min.setOnMouseExited(e->
		{
			Min.setPrefHeight(20);
			Min.setPrefWidth(20);
		});
		Close.setOnAction(e->
		{
			PStage.close();
			System.exit(0);

		});
		Max.setOnAction(e->
		{
			if(PStage.getHeight()==500)
			{
				PStage.setHeight(700);
				PStage.setWidth(675);
			}
			else
			{
				PStage.setHeight(500);
				PStage.setWidth(450);
			}

		});
		Min.setOnAction(e->
		{
				PStage.setIconified(true);
		});
        Body.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
		Body.setOnMouseDragged(new EventHandler<MouseEvent>()
		{
            @Override
            public void handle(MouseEvent event)
            {
                PStage.setX(event.getScreenX() - xOffset);
                PStage.setY(event.getScreenY() - yOffset);
            }
        });
		NBtAction(Zero,Screen);
		NBtAction(One,Screen);
		NBtAction(Two,Screen);
		NBtAction(Three,Screen);
		NBtAction(Four,Screen);
		NBtAction(Five,Screen);
		NBtAction(Six,Screen);
		NBtAction(Seven,Screen);
		NBtAction(Eight,Screen);
		NBtAction(Nine,Screen);
		OBtAction(Dot,Screen);
		OBtAction(Mod,Screen);
		OBtAction(Sub,Screen);
		OBtAction(Mul,Screen);
		OBtAction(Add,Screen);
		OBtAction(Div,Screen);
		OBtAction(Sqrt,Screen);
		OBtAction(RArr,Screen);
		OBtAction(LArr,Screen);
		OBtAction(Base,Screen);
		OBtAction(Clear,Screen);
		Equal.setOnAction(e->
		 {	
		 		Calculation cal=new Calculation();
				Results=cal.evaluateExpression(Results);
		 		Screen.setText(Results);
		 		Anwser=Results;
		 		Start=true;
		 });
		Ans.setOnAction(e->
		{
			 if(Results=="Invalid Input")
			 {
				 Screen.setText(Results);
			 }
			 else if(Start)
			 {
				 Results=Anwser;
				 Screen.setText(Results);
				 Start=false;
			 }
			else
		    {
				 Results+=Anwser;
				 Screen.setText(Results);
	    	 }
			 
		});
		Clear.setOnMouseClicked(e->
		{
			Results="";
			Screen.setText(Results);
		});
		Del.setOnMouseClicked(e->
		{
			 if(Results.length()!=0)
			 {
				 Results=Results.substring(0,Results.length()-1);
				 Screen.setText(Results);
			 }
			 else 
			 { 
				 Screen.setText("");
			 }
		});
	}
	public static VBox Buttons()
	{

		 VBox Block=new VBox(5);
		 HBox Raw1=new HBox(4);
		 HBox Raw2=new HBox(4);
		 HBox Raw3=new HBox(4);
		 HBox Raw4=new HBox(4);
			//Raw1
		 Seven=new Button("7");
		 Seven.setPrefSize(WidthListner/6,HeightListner/6);
		 Seven.setStyle("-fx-background-radius:17px 5px 5px 5px ;");
		 Eight=new Button("8");
		 Eight.setPrefSize(WidthListner/6,HeightListner/6);
		 Nine=new Button("9");
		 Nine.setPrefSize(WidthListner/6,HeightListner/6);
		 Div=new Button("/");
		 Div.setId("Div");
		 Div.setPrefSize(WidthListner/6,HeightListner/6);
		 Div.setStyle("-fx-background-radius:5px 17px 5px 5px ;");
		 Del=new Button("←");
		 Del.setPrefSize(WidthListner/6,HeightListner/6);
		 Del.setStyle("-fx-font-size:20;");
		 Clear=new Button("c");
		 Clear.setPrefSize(WidthListner/6,HeightListner/6);
		 Clear.setId("Clear");
		 //Raw2
		 Four=new Button("4");
		 Four.setPrefSize(WidthListner/6,HeightListner/6);
		 Five=new Button("5");
		 Five.setPrefSize(WidthListner/6,HeightListner/6);
		 Six=new Button("6");
		 Six.setPrefSize(WidthListner/6,HeightListner/6);
		 Mul=new Button("x");
		 Mul.setId("Mul");
		 Mul.setPrefSize(WidthListner/6,HeightListner/6);
		 LArr=new Button("(");
		 LArr.setId("LArr");
		 LArr.setPrefSize(WidthListner/6,HeightListner/6);
		 RArr=new Button(")");
		 RArr.setPrefSize(WidthListner/6,HeightListner/6);
		 RArr.setId("RArr");
		 //Raw3
		 One=new Button("1");
		 One.setPrefSize(WidthListner/6,HeightListner/6);
		 Two=new Button("2");
		 Two.setPrefSize(WidthListner/6,HeightListner/6);
		 Three=new Button("3");
		 Three.setPrefSize(WidthListner/6,HeightListner/6);
		 Sub=new Button("-");
		 Sub.setPrefSize(WidthListner/6,HeightListner/6);
		 Sub.setId("Sub");
		 Base=new Button("^");
		 Base.setPrefSize(WidthListner/6,HeightListner/6);
		 Base.setId("Base");
		 Sqrt=new Button("√");
		 Sqrt.setPrefSize(WidthListner/6,HeightListner/6);
		 Sqrt.setId("Sqrt");
		 //Raw4
		 Zero=new Button("0");
		 Zero.setPrefSize(WidthListner/6,HeightListner/6);
		 Zero.setStyle("-fx-background-radius:5px 5px 5px 17px ;");
		 Dot=new Button(".");
		 Dot.setPrefSize(WidthListner/6,HeightListner/6);
		 Dot.setId("Dot");
		 Mod=new Button("%");
		 Mod.setId("Mod");
		 Mod.setPrefSize(WidthListner/6,HeightListner/6);
		 Add=new Button("+");
		 Add.setId("Add");
		 Add.setPrefSize(WidthListner/6,HeightListner/6);
		 Equal=new Button("=");
		 Equal.setPrefSize((WidthListner)/6,HeightListner/6);
		 Equal.setId("Equal");
		 Ans=new Button("Ans");
		 Ans.setPrefSize((WidthListner)/6,HeightListner/6);
		 Ans.setId("Ans");
		 //Adding raws
		 Raw1.getChildren().addAll(Seven,Eight,Nine,Del,Clear,Div);
		 Raw2.getChildren().addAll(Four,Five,Six,LArr,RArr,Mul);
		 Raw3.getChildren().addAll(One,Two,Three,Sub,Base,Sqrt);
		 Raw4.getChildren().addAll(Zero,Dot,Mod,Ans,Add,Equal);
		 Block.getChildren().addAll(Raw1,Raw2,Raw3,Raw4);
		return Block;
	}
	static void NBtAction(Button Action,TextField screen)
	{
		
		Action.setOnMouseClicked(e->
		 {
			 
				 if(Results=="Invalid Input")
				 {
					 Results=Action.getText();
					 screen.setText(Results);
				 }
				 else if(Start)
				 {
					 Results=Action.getText();
					 screen.setText(Results);
					 Start=false;
				 }
				else
			    {
					 Results+=Action.getText();
					 screen.setText(Results);
		    	 }
				 
			 
		 });
	}
	static void OBtAction(Button Action,TextField screen)
	{
		
		Action.setOnMouseClicked(e->
		 {
			 
				 if(Results=="Invalid Input")
				 {
					 Results=Action.getText();
					 screen.setText(Results);
				 }
				 else
				 {
						 Results+=Action.getText();
						 screen.setText(Results);
						 Start=false;
				 }
			 
		 });
	}
	public static void main(String[]args)
	{
		Application.launch(args);
	}
}
