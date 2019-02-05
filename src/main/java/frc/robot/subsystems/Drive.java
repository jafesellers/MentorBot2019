package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem
{
    private TalonSRX Right;
    private TalonSRX Left;
    public Drive()
    {
            Right = new TalonSRX(1);
            Left = new TalonSRX(2);
    }

    @Override
    protected void initDefaultCommand() {

    }


}