package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

class motor{
    // final variables for easy switch implimentation 
    /*
     *                   TO-DO:
     * add ones for all possible motor controllers 
     * add implimentations and usability for all 
     */
    private final int TALON = 0;
    private final int JAGUAR = 1;
    private final int TALON_SRX = 2;
    
    /* 
     * the type variable is used in form 
     *
     * switch(type){
     *   case TALLON:
     *   ...
     *   break;
     *
     *   case JAGUAR:
     *   ...
     *   break ; 
     *   ... 
     * }
     *
     * any time in a metho you and to differentiate between 
     * the different type of controllers and there differnt implimentations
     */
    private int type = -1;

    //stored refference to the Speedcontroller passed in 
    private SpeedController motorController = null;
    
    private Encoder encoder = null;

    /** pass in the Speed controller that you want to wrap around and use 
     * auto detects the type of speed controller and sets everything up accordingly
     */
    public motor(SpeedController controller){
        motorController = controller;
        // check for is controller is a Tallon
        if (controller instanceof Talon){
            type = TALON;
        }
        // check to see if controller is a Jaguar
        else if (controller instanceof Jaguar){
            type = JAGUAR;
        }
        // check to see if controller is TalonSRX
        else if (controller instanceof TalonSRX){
            type = TALON_SRX;
        }
        // ADD NEW CONTROLLER CHECKS HERE !!!!!!!!

        // default fallback when passed something not recongnized
        else {
            /* TO-DO: 
             * decide what the fallback case is.
             * maybe throw SpeedControllers not yet supported exception
             */
        }
    }

    public motor(SpeedController controller,Encoder encoder){
        this(controller);
        this.encoder = encoder;
    }
    /**
     * sets the motor to a specific speed 
     * @param speed
     */
    public void setVoltage(double speed){
        /* TO-DO: 
         * make sure speed controllers is setup for speed 
         * not voltage also should impliment built in PID 
         * if encoders is available 
         */
        
        switch(type){
            case JAGUAR:
                Jaguar jaguar =(Jaguar) motorController;
                jaguar.set(speed);
                break;
            case TALON:
                Talon talon = (Talon) motorController;
                talon.set(speed);
                break;
            case TALON_SRX:
                TalonSRX talonSRX = (TalonSRX) motorController;
                talonSRX.set(ControlMode.Current, speed);
                break;
            
        }
    }

    /**
     * wrapper method for set inverted 
     * allows the programmer to easily compensate for inverted motor output
     * true sets the motor to inverted 
     * false sets it to uninverted
     * @param inverted
     */
    public void setInverted(boolean inverted){
        motorController.setInverted(inverted);
    }
    /**
     * wrapper method for the stop method.
     * makes it easy to stop the robot
     */
    public void stop(){
        motorController.stopMotor();
    }
}