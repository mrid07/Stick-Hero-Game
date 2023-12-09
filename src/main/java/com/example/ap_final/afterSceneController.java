package com.example.ap_final;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Thread.sleep;

public class afterSceneController {
    private static final double EXTEND_SPEED =2;
    private Line line;
    private int gap,angle=0;
    @FXML
    private Label welcomeText;
    private double startX=100;
    private double startY=460;
    private Image Hero;
    private Boolean stopStick=false;


    ArrayList<String> image_paths= new ArrayList<>(Arrays.asList("file:Images/p1.png","file:Images/p2.png","file:Images/p3.png","file:Images/p5.png","file:Images/p6.png"));
    ArrayList<Integer> image_size= new ArrayList<>(Arrays.asList(121,92,27,47,69,289));

    private  boolean extendingStick = false;
    private double characterBaseY = 550; // Adjust this value based on your character's base position
    private Timeline extendingTimeline;

    @FXML
    private ImageView Imagebg1;
    @FXML
    private ImageView Pillar1,Pillar2,hero;

    @FXML
    private AnchorPane gamePane;
    private int Score=0,Size_pillar2=0,Size_Pillar1=0;

    public void initialize(){
       // gamePane.getChildren().add(Hero);

        Random random=new Random();
        int Img_No= random.nextInt(1,5);
        Size_Pillar1=image_size.get(Img_No);
        String image=image_paths.get(Img_No);
        Image pillar=new Image(image);
        Pillar1=new ImageView(pillar);

        Pillar1.setX(100-Size_Pillar1);
        Pillar1.setY(460);
        startX=100-2;
        gamePane.getChildren().add(Pillar1);

        Hero=new Image("file:Images/char.png",30,30,false,false);
         hero=new ImageView(Hero);

        hero.setX((double) (100-3-30));
        hero.setY(430);
        gamePane.getChildren().add(hero);


        generateStick();
        generateRandomPillar();
        //startExtendingStick(line);
        /*updateStick(stick);


        gamePane.setOnMousePressed(event -> startExtendingStick(stick));
        gamePane.setOnMouseReleased(event -> stopExtendingStick(stick));*/
//        Boolean t=true;
//        while(t){
//            game
//        gamePane.setOnMousePressed(event -> {startExtendingStick(line)});
//        gamePane.setOnMouseReleased(event -> stopExtendingStick(line));
//        t=false;
//        }
        gamePane.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                if (!stopStick) {
                    System.out.println("Pressed");
                    extendingStick = true;
                    startStretchingAnimation();
                }
            }
        });gamePane.setOnMouseReleased(event -> {
            if (event.getButton()==MouseButton.PRIMARY) {
                if (!stopStick) {
                    stopStick=true;
                    System.out.println("Released");
                    extendingStick = false;
                    dropStick(line);

                }
            }
        });




        //checkScore();
        //dropStick(line);

    }


    public void loop(){
        Pillar1=Pillar2;
        generateStick();
        generateRandomPillar();
//        Boolean t=true;
//        while(t){
//            gamePane.setOnMousePressed(event -> startExtendingStick(line));
//            gamePane.setOnMouseReleased(event -> stopExtendingStick(line));
//            t=false;
//        }
        extendingStick=true;
        stopStick=false;
        gamePane.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                if (!stopStick) {
                    System.out.println("Pressed");
                    extendingStick = true;
                    startStretchingAnimation();
                }
            }
        });gamePane.setOnMouseReleased(event -> {
            if (event.getButton()==MouseButton.PRIMARY) {
                if (!stopStick) {
                    stopStick=true;
                    System.out.println("Released");
                    extendingStick = false;
                    dropStick(line);

                }
            }
        });

    }
    public void a(){
        int a;
    }


    public void print_Arr(ArrayList arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
    }}

//    @Override
//    public String toString() {
//        return "afterSceneController{" +
//                "image_paths=" + image_paths +
//                '}';
//    }

    public int findsize(ImageView ig){
        int index = 0;
        for(int i=0;i<5;i++){
            if(ig.getImage().equals(image_paths.get(i))){
                index=i;
            }
        }

//        print_Arr(image_paths);
//        print_Arr(image_size);
        System.out.println( ig.getImage());

        return image_size.get(index);
    }

    public void shiftScene(){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(Pillar1);
        translateTransition.setFromX(0);  // Starting X-coordinate
        translateTransition.setToX(-200);   // Ending X-coordinate
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.play();
        TranslateTransition translateTransition1 = new TranslateTransition();
        translateTransition1.setNode(Pillar2);
        translateTransition1.setFromX(0);  // Starting X-coordinate
        translateTransition1.setToX(56-Pillar2.getX());   // Ending X-coordinate
        translateTransition1.setDuration(Duration.seconds(1));
        translateTransition1.play();



    }

//    public void checkScore() {
//        System.out.println(startX-line.getEndY()+line.getStartY()+" "+Pillar2.getX()+" "+Size_pillar2);
//
//        if(Pillar2.getX()<-line.getEndY()+line.getStartY()+startX){
//            if(startX-line.getEndY()+line.getStartY()<Pillar2.getX()+Size_pillar2){
//                Score+=1;
//                System.out.println(Score);
//
//            }
//            else if(startX-line.getEndY()+line.getStartY()>Pillar2.getX()+Size_pillar2){
//            System.out.println(startX-line.getEndY()+line.getStartY()+" "+Pillar2.getX()+" "+findsize(Pillar2));
//            System.out.println("vccc");
//            TranslateTransition translateTransition1 = new TranslateTransition();
//            translateTransition1.setNode(hero);
//            translateTransition1.setFromX(-line.getEndY()+line.getStartY());  // Starting X-coordinate
//            translateTransition1.setToY(700-400);   // Ending X-coordinate
//            translateTransition1.setDuration(Duration.seconds(1));
//            translateTransition1.play();
//            //gamePane.getChildren().remove(hero);
//            }}
//        else if(Pillar2.getX()>-line.getEndY()+line.getStartY()+startX){
//            TranslateTransition translateTransition1 = new TranslateTransition();
//            translateTransition1.setNode(hero);
//            translateTransition1.setFromX(-line.getEndY()+line.getStartY());  // Starting X-coordinate
//            translateTransition1.setToY(700-400);   // Ending X-coordinate
//            translateTransition1.setDuration(Duration.seconds(1));
//            translateTransition1.play();
//           // gamePane.getChildren().remove(hero);
//        }
//    }


public void moveonstick(){
    TranslateTransition translateTransition = new TranslateTransition();
    translateTransition.setNode(hero);
    translateTransition.setFromX(0);  // Starting X-coordinate
    translateTransition.setToX(-line.getEndY()+line.getStartY());   // Ending X-coordinate
    translateTransition.setDuration(Duration.seconds(1));
    //translateTransition.play();


    //checkScore();

    System.out.println(startX-line.getEndY()+line.getStartY()+" "+Pillar2.getX()+" "+Size_pillar2);

    if(Pillar2.getX()<-line.getEndY()+line.getStartY()+startX){
        if(startX-line.getEndY()+line.getStartY()<Pillar2.getX()+Size_pillar2){
            Score+=1;
            System.out.println(Score);

            translateTransition.setToX(Pillar2.getX()+Size_pillar2-startX-2);
            //hero.setTranslateX(0);
//            hero.setX(Pillar2.getX()+Size_pillar2-startX-2);
            //translateTransition.play();
            //shiftScene();
            TranslateTransition translateTransition2 = new TranslateTransition();
            translateTransition2.setNode(Pillar1);
            translateTransition2.setFromX(-Pillar1.getX());  // Starting X-coordinate
            translateTransition2.setToX(-900);   // Ending X-coordinate
            translateTransition2.setDuration(Duration.seconds(1));
//            translateTransition2.play();
            TranslateTransition translateTransition1 = new TranslateTransition();
            translateTransition1.setNode(Pillar2);
            translateTransition1.setFromX(0);  // Starting X-coordinate
            translateTransition1.setToX((-Size_pillar2-Pillar2.getX()+100));   // Ending X-coordinate
            translateTransition1.setDuration(Duration.seconds(1));
            TranslateTransition translateTransition3 = new TranslateTransition();
            translateTransition3.setNode(hero);
            translateTransition3.setFromX(Pillar2.getX()+Size_pillar2-startX-2);
            translateTransition3.setToX((0));   // Ending X-coordinate
            translateTransition3.setDuration(Duration.seconds(1));


            TranslateTransition translateTransition4 = new TranslateTransition();
            translateTransition4.setNode(line);
            translateTransition4.setFromX(0);
            translateTransition4.setToX((line.getEndY()-line.getStartY()));   // Ending X-coordinate
            translateTransition4.setDuration(Duration.seconds(1));
            //translateTransition1.play();
            translateTransition.setOnFinished(actionEvent -> {
                hero.setTranslateX(0);
            });

            ParallelTransition seqT1 = new ParallelTransition (translateTransition1,translateTransition2,translateTransition3,translateTransition4);
            SequentialTransition seqT = new SequentialTransition (translateTransition,seqT1);

            //playing the transition
            seqT.play();

            seqT.setOnFinished(actionEvent -> {
                generateStick();
                loop();
            });

//            Timeline timeline = new Timeline(
//                    new KeyFrame(Duration.seconds(2), event -> {
//                        // Code to be executed after animation completes
//                        System.out.println("Animation completed!");
//                    })
//            );
//
//            // Set the timeline to play
//            timeline.play();
//
//            // Set the event handler for when the animation is finished
//            timeline.setOnFinished(event -> {
//                // Code to be executed after animation is finished
//                System.out.println("Animation setOnFinished completed!");
//            });

//            gamePane.getChildren().remove(line);
//            loop();
//            javafx.animation.KeyFrame keyFrame = new javafx.animation.KeyFrame(
//                    javafx.util.Duration.millis(2000),
//                    event -> gamePane.getChildren().remove(line)
//            );
//
//            javafx.animation.Timeline timeline = new javafx.animation.Timeline(keyFrame);
//            timeline.play();



        }
        else if(startX-line.getEndY()+line.getStartY()>Pillar2.getX()+Size_pillar2){
            System.out.println(startX-line.getEndY()+line.getStartY()+" "+Pillar2.getX()+" "+findsize(Pillar2));
            System.out.println("vccc");
            TranslateTransition translateTransition1 = new TranslateTransition();
            translateTransition1.setNode(hero);
            translateTransition1.setFromX(-line.getEndY()+line.getStartY());  // Starting X-coordinate
            translateTransition1.setToY(700-450);   // Ending X-coordinate
            translateTransition1.setDuration(Duration.seconds(0.5));
            SequentialTransition seqT = new SequentialTransition (translateTransition,translateTransition1);

            //playing the transitio
            seqT.play();}}
    else if(Pillar2.getX()>-line.getEndY()+line.getStartY()+startX){
        TranslateTransition translateTransition1 = new TranslateTransition();
        translateTransition1.setNode(hero);
        translateTransition1.setFromX(-line.getEndY()+line.getStartY());  // Starting X-coordinate
        translateTransition1.setToY(700-450);   // Ending X-coordinate
        translateTransition1.setDuration(Duration.seconds(0.5));
        SequentialTransition seqT = new SequentialTransition (translateTransition,translateTransition1);

        //playing the transition
        seqT.play();
    }








//    Timeline time= new Timeline(new KeyFrame(Duration.millis(40), e -> moveonstick_1()));
//    time.setCycleCount((int) ());
//    System.out.println(line.getEndX()+" "+line.getStartX());
//    time.play();

//    TranslateTransition translate = new TranslateTransition(Duration.seconds(0.5));
////
////        //shifting the X coordinate of the centre of the circle by 400
//    translate.setToX(hero.getX()+gap+findsize(Pillar1)-30);
////
////        //setting the duration for the Translate transition
//    translate.setDuration(Duration.millis(1000));
////
////        //setting cycle count for the Translate transition
//    translate.setNode(hero);
//    translate.play();
//    translate.setOnFinished(e->{
//        Pillar2.setTranslateX(0);
//        Pillar2.setX(hero.getX()+gap+findsize(Pillar1)-30);
//});

}
    public void moveonstick_1(){
//        TranslateTransition translate = new TranslateTransition();
//
//        //shifting the X coordinate of the centre of the circle by 400
////        translate.setFromX(line.getEndX());
//
//        //setting the duration for the Translate transition
//        translate.setDuration(Duration.millis(1000));
//
//        //setting cycle count for the Translate transition
//        translate.setCycleCount(500);
//        System.out.println("ddddd");
//        translate.setNode(hero);
//        translate.play();

        Translate translate = new Translate();

        //setting the properties of the translate object
        translate.setX(hero.getX()+1);
//        translate.setY();
        //translate.setZ(200);

        //applying transformation to the second rectangle
        hero.getTransforms().addAll(translate);

    }

    private void generateRandomPillar() {


        Random random=new Random();
        int Img_No= random.nextInt(1,5);
        int size=image_size.get(Img_No);
        String image=image_paths.get(Img_No);
        Size_pillar2=image_size.get(Img_No);
        Image pillar=new Image(image);
         Pillar2=new ImageView(pillar);
        Random random1=new Random();
         gap=random1.nextInt(56+size+70,56+size+129);
        System.out.println(56+size);
        System.out.println(gap);
        System.out.println(56+size+109);
        Pillar2.setX(gap);
        Pillar2.setY(460);
        gamePane.getChildren().add(Pillar2);
//        /*Random random = new Random();
//        double minDistance = 50; // Set your minimum desired distance
//        double maxDistance = 400; // Set your maximum desired distance
//        double minPillarWidth = 50;
//        double maxPillarWidth = 100;
//        double distance = minDistance + (maxDistance - minDistance) * random.nextDouble();
//
//        double newX = pillar.getLayoutX() + distance;
//
//        Rectangle newPillar = new Rectangle();
//        newPillar.setWidth(minPillarWidth + (maxPillarWidth - minPillarWidth) * random.nextDouble());
//        newPillar.setHeight(pillar.getHeight());
//        newPillar.setLayoutX(newX);
//        newPillar.setLayoutY(283);
//
//        gamePane.getChildren().add(newPillar);
//
//        // Generate a stick after adding the new pillar
//        Rectangle stick = generateStick();
//
//        // Update and drop the stick
//        updateStick(stick);

    }
    private double getHeight(){
        return line.getEndY()-line.getStartY();
    }

    private void rotateAndMovePlayer( ) {
        Rotate rotate = new Rotate();
        rotate.setPivotX(startX);
        rotate.setPivotY(startY);
        line.getTransforms().setAll(rotate);

        AnimationTimer rotationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (angle < 91) {
                    rotate.setAngle(angle);
                    angle++;
                } else {
                    this.stop();
                    angle = 0;
                    //System.out.println("Length is "+ length);
                    moveonstick();
                }
            }
        };


        rotationTimer.start();
    }




    private void rotateLine(Line stick) {
        Rotate rotate = new Rotate();

        //setting properties for the rotate object.
        rotate.setAngle(1);
        rotate.setPivotX(startX);
        rotate.setPivotY(startY);

        //rotating the 2nd rectangle.
        line.getTransforms().add(rotate);

    }
    private void dropStick(Line stick) {


        Timeline time= new Timeline(new KeyFrame(Duration.millis(4), e -> rotateLine(line)));
        time.setCycleCount(90);
        time.play();
        moveonstick();
//        t.start();
//        t.join();
//        gamePane.getChildren().remove(line);
//        generateStick();
//        loop();
    }

    private void startExtendingStick(Line stick) {
        extendingStick = true;

        extendingTimeline = new Timeline(new KeyFrame(Duration.millis(16), e -> extendStick(stick)));
        extendingTimeline.setCycleCount(Timeline.INDEFINITE);
        extendingTimeline.play();
    }

    private void startStretchingAnimation() {
        double maxLength = 600;
        new Thread(() -> {
            while (extendingStick) {
                try {
                    double initialLength = line.getStartY() - line.getEndY();
                    if (initialLength >= maxLength) {
                        extendingStick = false;
                        break;
                    }
                    if (initialLength < maxLength) {
                        double endY = line.getEndY() - 1;
                        if (endY >= 0) {
                            line.setEndY(endY);
                        }
                    }
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void extendStick(Line stick) {
        // Add logic to extend the stick while the mouse is pressed
        if (extendingStick) {
            // Increase the stick width based on the extension speed
            stick.setEndY(stick.getEndY()- EXTEND_SPEED);
            //stick.setHeight(stick.getHeight() + EXTEND_SPEED);
        }


    }

    private void stopExtendingStick(Line stick) {
        extendingStick = false;

        if (extendingTimeline != null) {
            extendingTimeline.stop();
        }
    }
    private void generateStick() {


        line = new Line(100, 460, 100, 460 - 10);
        gamePane.getChildren().add(line);



    }
///*
//    // Set up mouse event handlers
//        gamePane.setOnMousePressed(event -> handleMousePressed());
//        gamePane.setOnMouseDragged(event -> handleMouseDragged());
//        gamePane.setOnMouseReleased(event -> handleMouseReleased());*/
//
//        /*primaryStage.setScene(new Scene(root, 400, 400));
//        primaryStage.setTitle("Extendable Line");
//        primaryStage.show();*/
//        //return line;


//    private void handleMousePressed() {
//        // Update the starting point of the line
//        startX = line.getEndX();
//        startY = line.getEndY();
//    }
//
//    private void handleMouseDragged() {
//        // Update the end point of the line while the mouse is dragged
//        line.setEndX(line.getStartX() + (startX - line.getStartX()) + (line.getEndX() - startX));
//        line.setEndY(line.getStartY() + (startY - line.getStartY()) + (line.getEndY() - startY));
//    }

//    /*private void handleMouseReleased() {
//        *//*//*/ Do any additional actions when the mouse button is released
//    *//*
//    }*/


//    private Line generateStick() {
//
//        Line stick = new Line(126,406,150,406);
//        stick.setStrokeWidth(1); // Set your desired stick width
//        //stick.setStartX(characterBaseY - pillar.getLayoutY());
//
//        // Position the stick at the end of the current pillar
//        //stick.setLayoutX(pillar.getLayoutX(); /* +pillar.getWidth());
//        stick.setLayoutY(pillar.getLayoutY());
//
//        gamePane.getChildren().add(stick);
//
//        return stick;
//    }
//    private void startExtendingStick(Rectangle stick) {
//        extendingStick = true;
//
//        extendingTimeline = new Timeline(new KeyFrame(Duration.millis(16), e -> extendStick(stick)));
//        extendingTimeline.setCycleCount(Timeline.INDEFINITE);
//        extendingTimeline.play();
//    }

//
//    private void extendStick(Rectangle stick) {
//        // Add logic to extend the stick while the mouse is pressed
//        if (extendingStick) {
//            // Increase the stick width based on the extension speed
//            stick.setWidth(stick.getWidth() + EXTEND_SPEED);
//            //stick.setHeight(stick.getHeight() + EXTEND_SPEED);
//        }
//    }
//
//    private void stopExtendingStick(Rectangle stick) {
//        extendingStick = false;
//
//        if (extendingTimeline != null) {
//            extendingTimeline.stop();
//        }
//        // Add logic to stop extending the stick when the mouse is released
//        // For example, initiate the dropStick method
//    }




    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}