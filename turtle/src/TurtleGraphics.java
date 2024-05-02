import javax.swing.JFrame;
import uk.ac.leedsbeckett.oop.OOPGraphics;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;  
import javax.swing.*;

//                               ___-------___
//                           _-~~             ~~-_
//                        _-~                    /~-_
//     /^\__/^\         /~  \                   /    \
//   /|  O|| O|        /      \_______________/        \
//  | |___||__|      /       /                \          \
//  |          \    /      /                    \          \
//  |   (_______) /______/                        \_________ \
//  |         / /         \                      /            \
//   \         \^\\         \                  /               \     /
//     \         ||           \______________/      _-_       //\__//
//       \       ||------_-~~-_ ------------- \ --/~   ~\    || __/
//         ~-----||====/~     |==================|       |/~~~~~
//          (_(__/  ./     /                    \_\      \.
 //                (_(___/       -turtle-           \_____)_)


public class TurtleGraphics extends OOPGraphics {
    private Commands commandsList = new Commands();

    public static void main(String[] args) {
        new TurtleGraphics();
    }

    public TurtleGraphics() {
        JFrame MainFrame = new JFrame();                
        MainFrame.setLayout(new FlowLayout());  
        MainFrame.add(this);                                  
        MainFrame.pack();                                             
        MainFrame.setVisible(true);   
        displayMessage("Rosie's Turtle");                       
        about();                                                               
    }

    public void processCommand(String command) {
        String[] cmdSection = command.split(" ");
        String cmd = cmdSection[0]; 
        int parameter = 0;

        switch (cmd.toLowerCase()) {
            case "penup":
                penUp();
                break;
            case "pendown":
                penDown();
                break;
            case "penwidth":
                if (cmdSection.length >= 2) {
                    try {
                        parameter = Integer.parseInt(cmdSection[1]);
                        setStroke(parameter);
                    } catch (NumberFormatException e) {
                        displayMessage("Invalid parameter for penwidth: " + cmdSection[1]);
                    }
                } else {
                    displayMessage("Missing parameter for penwidth");
                }
                break;
            case "forward":
                if (cmdSection.length >= 2) {
                    try {
                        parameter = Integer.parseInt(cmdSection[1]);
                        int x = getxPos() + (int) (parameter * Math.cos(Math.toRadians(getDirection())));
                        int y = getyPos() + (int) (parameter * Math.sin(Math.toRadians(getDirection())));
                        if (Bounds.isWithinBounds(x, y)) {
                            super.forward(parameter);
                        } else {
                            displayMessage("Cannot move forward. Would be Out of bounds.");
                        }
                    } catch (NumberFormatException e) {
                        displayMessage("Invalid parameter for forward: " + cmdSection[1]);
                    }
                } else {
                    displayMessage("Missing parameter for forward");
                }
                break;
            case "backward":
                    if (cmdSection.length >= 2) {
                        try {
                            parameter = Integer.parseInt(cmdSection[1]);
                            int x = getxPos() - (int) (parameter * Math.cos(Math.toRadians(getDirection())));
                            int y = getyPos() - (int) (parameter * Math.sin(Math.toRadians(getDirection())));
                            if (Bounds.isWithinBounds(x, y)) {
                                super.forward(parameter * -1);
                            } else {
                                displayMessage("Cannot move backward. Would be out of bounds.");
                            }
                        } catch (NumberFormatException e) {
                            displayMessage("Invalid parameter for backward: " + cmdSection[1]);
                        }
                    } else {
                        displayMessage("Missing parameter for backward");
                    }
                    break;
            case "turnright":
                if (cmdSection.length >= 2) {
                    try {
                        parameter = Integer.parseInt(cmdSection[1]);
                        turnRight(parameter);
                    } catch (NumberFormatException e) {
                        displayMessage("Invalid parameter for turnright: " + cmdSection[1]);
                    }
                } else {
                    displayMessage("Missing parameter for turnright");
                }
                break;
            case "turnleft":
                if (cmdSection.length >= 2) {
                    try {
                        parameter = Integer.parseInt(cmdSection[1]);
                        turnLeft(parameter);
                    } catch (NumberFormatException e) {
                        displayMessage("Invalid parameter for turnleft: " + cmdSection[1]);
                    }
                } else {
                    displayMessage("Missing parameter for turnleft");
                }
                break;
            case "square":
                if (cmdSection.length >= 2) {
                    try {
                        parameter = Integer.parseInt(cmdSection[1]);
                        square(parameter);
                    } catch (NumberFormatException e) {
                        displayMessage("Invalid parameter for square: " + cmdSection[1]);
                    }
                } else {
                    displayMessage("Missing parameter for square");
                }
                break;
            case "triangle":
                if (cmdSection.length == 2) {
                    try {
                        parameter = Integer.parseInt(cmdSection[1]);
                        triangle(parameter);
                    } catch (NumberFormatException e) {
                        displayMessage("Invalid parameter for triangle: " + cmdSection[1]);
                    }
                } else if (cmdSection.length == 4) {
                    try {
                        int side1 = Integer.parseInt(cmdSection[1]);
                        int side2 = Integer.parseInt(cmdSection[2]);
                        int side3 = Integer.parseInt(cmdSection[3]);
                        triangle(side1, side2, side3);
                    } catch (NumberFormatException e) {
                        displayMessage("Invalid parameters for triangle: " + cmdSection[1] + ", " + cmdSection[2] + ", " + cmdSection[3]);
                    }
                } else {
                    displayMessage("Invalid parameters for triangle");
                }
                break;
            case "red":
                red();
                break;
            case "green":
                green();
                break;
            case "blue":
                blue();
                break;
            case "white":
                white();
                break;
            case "pen":
                if (cmdSection.length == 4) {
                    try {
                        int red = Integer.parseInt(cmdSection[1]);
                        int green = Integer.parseInt(cmdSection[2]);
                        int blue = Integer.parseInt(cmdSection[3]);
                        colour(red, green, blue);
                    } catch (NumberFormatException e) {
                        displayMessage("Invalid parameters for colour: " + cmdSection[1] + ", " + cmdSection[2] + ", " + cmdSection[3]);
                    }
                } else {
                    displayMessage("Invalid parameters for colour");
                }
                break;
            case "reset":
                reset();
                break;
            case "clear":
                clear();
                break;
            case "about":
                about();
                break;
            case "saveimg":
                if (cmdSection.length >= 2) {
                    saveImg(cmdSection[1]);
                } else {
                    displayMessage("Missing parameter for saveimg");
                }
                break;
            case "loadimg":
                if (cmdSection.length >= 2) {
                    loadImg(cmdSection[1]);
                } else {
                    displayMessage("Missing parameter for loadimg");
                }
                break;
            case "savetxt":
                if (cmdSection.length >= 2) {
                    saveTxt(cmdSection[1]);
                } else {
                    displayMessage("Missing parameter for savetxt");
                }
                break;
            case "loadtxt":
                if (cmdSection.length >= 2) {
                    LoadTxt(cmdSection[1]);
                } else {
                    displayMessage("Missing parameter for loadtxt");
                }
                break;
            default:
                displayMessage("Invalid command");
                break;
        }
    }

    // commands
    //Penup
    @Override
    public void penUp() {
        super.penUp();
        displayMessage("Penup");
        commandsList.add("penup");
    }

    // Pendown
    @Override
    public void penDown() {
        super.penDown();
        displayMessage("Pendown");
        commandsList.add("pendown");
    }

    // Pen Width
    @Override
    public void setStroke(int width) {
        super.setStroke(width);
        displayMessage("Pen width set to " + width);
        commandsList.add("penwidth " + width);
    }

    // Forward 
    @Override
    public void forward(int distance) {
        super.forward(distance);
        displayMessage("Moved forward by " + distance + " units");
        commandsList.add("forward " + distance);
    }

    // Backwards
    public void backward(int distance) {
        super.forward(distance * -1);
        displayMessage("Moved backward by " + distance + " units");
        commandsList.add("backward " + distance);
    }

    // Turn right
    @Override
    public void turnRight(int degrees) {
        super.turnRight(degrees);
        displayMessage("Turned right by " + degrees + " degrees");
        commandsList.add("turnright " + degrees);
    }

    // Turn left
    @Override
    public void turnLeft(int degrees) {
        super.turnLeft(degrees);
        displayMessage("Turned left by " + degrees + " degrees");
        commandsList.add("turnleft " + degrees);
    }

    // square
    public void square(int side) {
        for (int i = 0; i < 4; i++) {
            forward(side);
            turnRight(90);
        }
        displayMessage("Drew a square with side length " + side);
    }
            
    // triangle < >
    public void triangle(int size) {
        for (int i = 0; i < 3; i++) {
            forward(size);
            turnRight(120);
        }
        displayMessage("Drew an equilateral triangle with side length " + size);
    }

    // triangle < > < > < > 

    public void triangle(int side1, int side2, int side3) {
        Graphics2D Graphics = (Graphics2D) getGraphics();
        Graphics.setColor(getPenColour());
        Graphics.setStroke(new BasicStroke(getStroke()));
        
        int[] xPoints = {getxPos(), side1, side2};
        int[] yPoints = {getyPos(), 0, side3};
        
        Graphics.drawPolygon(xPoints, yPoints, 3);
        displayMessage("Drew a three sided triangle with the sides: " + side1 + ", " + side2 + ", " + side3);
        commandsList.add("triangle " + side1 + " " + side2 + " " + side3);
    }

    // colour red
    public void red() {
        setPenColour(Color.RED);
        displayMessage("Changed pen colour to red");
        commandsList.add("red");
    }

    // colour green
    public void green() {
        setPenColour(Color.GREEN);
        displayMessage("Changed pen colour to green");
        commandsList.add("green");
    }

    // colour blue
    public void blue() {
        setPenColour(Color.BLUE);
        displayMessage("Changed pen colour to blue");
        commandsList.add("blue");
    }

    // colour white
    public void white() {
        setPenColour(Color.WHITE);
        displayMessage("Changed pen colour to white");
        commandsList.add("white");
    }

    // colour < > < > < >
    public void colour(int red, int green, int blue) {
        Color colour = new Color(red, green, blue);
        if (red >= 0 && red <= 255 && green >= 0 && green <= 255 && blue >= 0 && blue <= 255) {
            setPenColour(colour);
            displayMessage("Changed pen colour to RGB(" + red + ", " + green + ", " + blue + ")");
            commandsList.add("colour " + red + " " + green + " " + blue);
        } else {
            displayMessage("Invalid colour values. Colour values must be between 0 and 255.");
        }
    }

    // reset 
    @Override
    public void reset() {
        super.reset();
        super.penUp();
        displayMessage("Reset turtle position and pen");
        commandsList.add("reset");
    }

    // clear 
    @Override
    public void clear() {
        super.clear();
        displayMessage("Cleared the drawing");
        //commandsList.add("clear");
    }

    // about
    @Override
    public void about() {
        super.about();
        displayMessage("About Rosie's Turtle");
        commandsList.add("about");
    }

    // SaveImg
    public void saveImg(String filename) {
        try {
            BufferedImage img = getBufferedImage();  
            File outputfile = new File("./saves/" + filename + ".png");
            ImageIO.write(img, "png", outputfile);  
            displayMessage("Saved turtle image as " + filename + ".png");
        } catch (Exception error) {
            displayMessage("Error! Couldn't save the image as png: " + error.getMessage());
            Warning("Error! Couldn't save the image as png: " + error.getMessage());
        }
    }

    // LoadImg    
    public void loadImg(String filename) {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to load a new image? This will wipe over the current drawing.", "Warning", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            clear();
            reset();
            try {
                BufferedImage img = ImageIO.read(new File("./saves/" + filename + ".png"));
                setBufferedImage(img);
                displayMessage("Loaded image: " + filename + ".png");
            } catch (IOException error) {
                displayMessage("Error! Couldn't load image: " + error.getMessage());
                Warning("Error! Couldn't load image: " + error.getMessage());
            }
        } else {
            displayMessage("Image loading cancelled.");
        }
        
    }

    // SaveTxt
    public void saveTxt(String filename) {
        try {
            FileWriter fileWriter = new FileWriter("saves/" + filename + ".txt");
            fileWriter.write(Commands.ArrayToString((String[]) commandsList.getCommands()));
            fileWriter.close();
            displayMessage("Saved commands to " + filename + ".txt");
        } catch (IOException e) {
            displayMessage("Error saving file: " + e.getMessage());
            Warning("Error saving file: " + e.getMessage());
        }
    }

    // LoadTxt
    public void LoadTxt(String filename) {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to load a new image? This will wipe over the current drawing.", "Warning", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                clear();
                reset();
                FileReader fr = new FileReader("saves/" + filename + ".txt");
                BufferedReader br = new BufferedReader(fr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
                br.close();
                String[] loadedCommands = commandsList.StringToArray(sb.toString());
                displayMessage("Loaded " + loadedCommands.length + " commands from " + filename + ".txt");
                for (String command : loadedCommands) {
                    processCommand(command);
                }
            } catch (IOException e) {
                displayMessage("Error loading file: " + e.getMessage());
                Warning("Error loading file: " + e.getMessage());
            }
        } else {
            displayMessage("Image loading cancelled.");
        }
    }

    public void Warning(String warningmsg) {
        JOptionPane.showMessageDialog(null, warningmsg, "Warning", JOptionPane.WARNING_MESSAGE);
    }
}

class Commands {
    public String[] commands = new String[0];

    public void add(String command) {
        String[] temp = new String[commands.length + 1];
        for (int i = 0; i < commands.length; i++) {
            temp[i] = commands[i];
        }
        temp[commands.length] = command;
        commands = temp;
    }

    public String[] getCommands() {
        return commands;
    }

    public static String ArrayToString(String[] array) {
        StringBuilder builder = new StringBuilder();
        for (String item : array) {
            builder.append("| ").append(item);
        }
        builder.append("|");
        return builder.toString();
    }

    public String[] StringToArray(String string) {
        String[] array = string.split("\\|");
        return array;
    }
}

class Bounds {
    private static int xMin = 0;
    private static int xMax = 800;
    private static int yMin = 0;
    private static int yMax = 400;

    public static boolean isWithinBounds(int x, int y) {
        boolean result = (x >= xMin && x <= xMax && y >= yMin && y <= yMax);
        return result;
    }
}
