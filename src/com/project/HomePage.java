package com.project;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class HomePage extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public SerialPort port;
    JComboBox<String> portList;
    JButton connectButton;
    JButton logout;
    SerialPort[] portNames;
    JLabel lightLabel, light1Label, light2Label, light3Label, doorlabel, door1label, door2label, door3label, security, alarmlabel, autolabel;
    JToggleButton light1, light2, light3, door1, door2, door3, alarmbutton, autolight;

    public HomePage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(280, 120, 1014, 597);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        portList = new JComboBox<String>();
        portNames = SerialPort.getCommPorts();
        for (int i = 0; i < portNames.length; i++)
            portList.addItem(portNames[i].getSystemPortName());
        contentPane.add(portList);

        buildLogoutButton();
        contentPane.add(logout);

        buildConnectButton();
        contentPane.add(connectButton);

        buildLightLabel();
        contentPane.add(lightLabel);

        buildLight1();
        contentPane.add(light1);
        contentPane.add(light1Label);

        buildLight2();
        contentPane.add(light2);
        contentPane.add(light2Label);

        buildLight3();
        contentPane.add(light3);
        contentPane.add(light3Label);

        buildDoorLabel();
        contentPane.add(doorlabel);

        buildDoor1();
        contentPane.add(door1);
        contentPane.add(door1label);

        buildDoor2();
        contentPane.add(door2);
        contentPane.add(door2label);

        buildSecurityLabel();
        contentPane.add(security);

        buildAlarm();
        contentPane.add(alarmbutton);
        contentPane.add(alarmlabel);

        buildAutoLight();
        contentPane.add(autolight);
        contentPane.add(autolabel);

    }


    public void buildLogoutButton() {
        logout = new JButton("Logout");
        logout.setForeground(new Color(0, 0, 0));
        logout.setBackground(UIManager.getColor("Button.disabledForeground"));
        logout.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        logout.setBounds(880, 14, 100, 40);
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    Login loginpage = new Login();
                    loginpage.setTitle("Home admin log in");
                    loginpage.setVisible(true);
                }

            }
        });
    }

    public void buildConnectButton() {
        connectButton = new JButton("Connect");
        connectButton.setFocusable(false);
        connectButton.setBounds(20, 15, 200, 40);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (connectButton.getText().equals("Connect")) {
                    port = SerialPort.getCommPort(portList.getSelectedItem().toString());
                    port.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
                    if (port.openPort()) {
                        connectButton.setText("Disconnect");
                        portList.setEnabled(false);
                    }
                } else {

                    port.closePort();
                    portList.setEnabled(true);
                    connectButton.setText("Connect");
                }
            }
        });
    }

    public void buildLightLabel() {

        lightLabel = new JLabel();
        lightLabel.setForeground(Color.BLUE);
        lightLabel.setFont(new Font("Verdana", Font.PLAIN, 22));
        lightLabel.setText("Control The Status of Your lights from here");
        lightLabel.setBounds(190, 100, 500, 40);

    }

    public void buildLight1() {
        light1 = new JToggleButton("ON/OFF");
        light1Label = new JLabel();
        light1.setBounds(150, 140, 100, 40);
        ItemListener light1itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                int mainlight = 0;
                PrintWriter output = new PrintWriter(port.getOutputStream());
                if (state == ItemEvent.SELECTED) {
                    if (mainlight == 0) {
                        output.print("1");
                        output.flush();
                        System.out.println("main room light is on");
                        mainlight = 1;
                    }
                    light1.setText("ON");
                } else {
                    output.print("2");
                    output.flush();
                    System.out.println("main room light is off");
                    mainlight = 0;
                    light1.setText("OFF");
                }

            }
        };
        light1.addItemListener(light1itemListener);
        light1Label.setForeground(Color.GREEN);
        light1Label.setFont(new Font("Verdana", Font.PLAIN, 15));
        light1Label.setText("Main Room Light");
        light1Label.setBounds(150, 190, 280, 40);
    }

    public void buildLight2() {
        light2 = new JToggleButton("ON/OFF");
        light2Label = new JLabel();
        light2.setBounds(400, 140, 100, 40);
        ItemListener light2itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                int verandalight = 0;
                PrintWriter output = new PrintWriter(port.getOutputStream());
                if (state == ItemEvent.SELECTED) {
                    if (verandalight == 0) {
                        output.print("3");
                        System.out.println("Veranda light is on");
                        output.flush();
                        verandalight = 3;
                    }
                    light2.setText("ON");
                } else {
                    output.print("4");
                    output.flush();
                    System.out.println("veranda light is off");
                    verandalight = 4;
                    light2.setText("OFF");
                }

            }
        };
        light2.addItemListener(light2itemListener);
        light2Label.setForeground(Color.GREEN);
        light2Label.setFont(new Font("Verdana", Font.PLAIN, 15));
        light2Label.setText("Bed Room Light");
        light2Label.setBounds(400, 190, 280, 40);

    }

    public void buildLight3() {
        light3 = new JToggleButton("ON/OFF");
        light3Label = new JLabel();
        light3.setBounds(650, 140, 100, 40);
        ItemListener light3itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    ProcessBuilder light3on = new ProcessBuilder();
                    light3on.command("/bin/bash", "-c", "cd Files/sketchbook/light3on; make upload");
                    try {
                        Process light3onprocess = light3on.start();
                        StringBuilder light2onstring = new StringBuilder();
                        BufferedReader light3onReader = new BufferedReader(
                                new InputStreamReader(light3onprocess.getInputStream()));
                        String light3onLine;
                        while ((light3onLine = light3onReader.readLine()) != null) {
                            light2onstring.append(light3onLine + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    light3.setText("ON");
                } else {
                    ProcessBuilder light3off = new ProcessBuilder();
                    light3off.command("/bin/bash", "-c", "cd Files/sketchbook/light3off; make upload");//cmd.exe for windows
                    try {
                        Process light2process = light3off.start();
                        StringBuilder light3offString = new StringBuilder();
                        BufferedReader light3command = new BufferedReader(
                                new InputStreamReader(light2process.getInputStream()));
                        String line;
                        while ((line = light3command.readLine()) != null) {
                            light3offString.append(line + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    light3.setText("OFF");
                }

            }
        };
        light3.addItemListener(light3itemListener);

        light3Label.setForeground(Color.GREEN);
        light3Label.setFont(new Font("Verdana", Font.PLAIN, 15));
        light3Label.setText("Veranda Lights");
        light3Label.setBounds(650, 190, 280, 40);

    }

    public void buildDoorLabel() {
        doorlabel = new JLabel();
        doorlabel.setForeground(Color.BLUE);
        doorlabel.setFont(new Font("Verdana", Font.PLAIN, 22));
        doorlabel.setText("Control The Status of Your Door From here");
        doorlabel.setBounds(190, 220, 500, 40);

    }

    public void buildDoor1() {
        door1 = new JToggleButton("OPEN/CLOSE");
        door1label = new JLabel();
        door1.setBounds(150, 250, 100, 40);
        ItemListener door1itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    ProcessBuilder opendoor = new ProcessBuilder();
                    opendoor.command("/bin/bash", "-c", "cd Files/sketchbook/Opendoor; make upload");
                    try {
                        Process openprocess = opendoor.start();
                        StringBuilder doorString = new StringBuilder();
                        BufferedReader doorReader = new BufferedReader(new InputStreamReader(openprocess.getInputStream()));
                        String doorLine;
                        while ((doorLine = doorReader.readLine()) != null) {
                            doorString.append(doorLine + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    door1.setText("OPEN");
                } else {
                    ProcessBuilder closeProcess = new ProcessBuilder();
                    closeProcess.command("/bin/bash", "-c", "cd Files/sketchbook/CloseDoor; make upload");
                    try {
                        Process doorcloseprocess = closeProcess.start();
                        StringBuilder closestring = new StringBuilder();
                        BufferedReader closeReader = new BufferedReader(new InputStreamReader(doorcloseprocess.getInputStream()));
                        String closeline;
                        while ((closeline = closeReader.readLine()) != null) {
                            closestring.append(closeline + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    door1.setText("CLOSE");
                }
            }
        };
        door1.addItemListener(door1itemListener);

        door1label.setForeground(Color.GREEN);
        door1label.setFont(new Font("Verdana", Font.PLAIN, 15));
        door1label.setText("Front Door");
        door1label.setBounds(150, 300, 280, 40);

    }

    public void buildDoor2() {
        door2 = new JToggleButton("OPEN/CLOSE");
        door2label = new JLabel();
        door2.setBounds(400, 250, 100, 40);
        ItemListener door2itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    door2.setText("OPEN");
                } else {
                    door2.setText("CLOSE");
                }
            }
        };
        door2.addItemListener(door2itemListener);


        door2label.setForeground(Color.GREEN);
        door2label.setFont(new Font("Verdana", Font.PLAIN, 15));
        door2label.setText("Inner Door");
        door2label.setBounds(400, 300, 280, 40);

    }

    public void buildDoor3() {
        door3 = new JToggleButton("OPEN/CLOSE");
        door3label = new JLabel();
        door3.setBounds(650, 250, 100, 40);
        ItemListener door3itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    door3.setText("OPEN");
                } else {
                    door3.setText("CLOSE");
                }
            }
        };
        door3.addItemListener(door3itemListener);


        door3label.setForeground(Color.GREEN);
        door2label.setFont(new Font("Verdana", Font.PLAIN, 15));
        door3label.setText("Back Door");
        door3label.setBounds(650, 300, 280, 40);

    }

    public void buildSecurityLabel() {
        security = new JLabel();
        security.setText("Security Log");
        security.setForeground(Color.BLUE);
        security.setFont(new Font("Verdana", Font.PLAIN, 22));
        security.setBounds(190, 400, 500, 40);
    }

    public void buildAlarm(){
        alarmlabel = new JLabel();
        alarmbutton = new JToggleButton("Activate/Deactivate");
        alarmlabel.setText("Control your alarm system");
        alarmbutton.setBounds(190, 450,200,90);
        ItemListener alarmItemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                int state  = itemEvent.getStateChange();
                if (state==itemEvent.SELECTED) {
                    ProcessBuilder alarmon = new ProcessBuilder();
                    alarmon.command("/bin/bash", "-c", "cd Files/sketchbook/alarm; make upload");
                    try {
                        Process alarmprocess = alarmon.start();
                        StringBuilder alarmstring = new StringBuilder();
                        BufferedReader alarmread = new BufferedReader(
                                new InputStreamReader(alarmprocess.getInputStream()));
                        String alarmLine;
                        while ((alarmLine = alarmread.readLine()) != null) {
                            alarmstring.append(alarmLine + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    alarmbutton.setText("Activated");
                }
                else{
                    ProcessBuilder deactivate = new ProcessBuilder();
                    deactivate.command("/bin/bash", "-c", "cd Files/sketchbook/deactivate; make upload");
                    try{
                        Process  deactivateprocess = deactivate.start();
                        StringBuilder deactivatestring = new StringBuilder();
                        BufferedReader deactivateread = new BufferedReader(new InputStreamReader(deactivateprocess.getInputStream()));
                        String deactivateLine;
                        while((deactivateLine=deactivateread.readLine())!= null){
                            deactivatestring.append(deactivateLine+"\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    alarmbutton.setText("Deactivated");
                }
            }

        };
        alarmbutton.addItemListener(alarmItemListener);


    }
    public void buildAutoLight() {
        autolabel = new JLabel();
        autolabel.setForeground(Color.BLUE);
        autolabel.setFont(new Font("Verdana", Font.PLAIN, 26));
        autolabel.setText("Control Automatic light");
        autolabel.setBounds(460, 380, 200, 90);
        autolight = new JToggleButton("Activate/Deactivate");
        autolight.setText("Auto Light");
        autolight.setBounds(460, 450, 200, 90);
        ItemListener light = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if (state == itemEvent.SELECTED) {
                    ProcessBuilder alarmon = new ProcessBuilder();
                    alarmon.command("/bin/bash", "-c", "cd Files/sketchbook/auto; make upload");
                    try {
                        Process alarmprocess = alarmon.start();
                        StringBuilder alarmstring = new StringBuilder();
                        BufferedReader alarmread = new BufferedReader(
                                new InputStreamReader(alarmprocess.getInputStream()));
                        String alarmLine;
                        while ((alarmLine = alarmread.readLine()) != null) {
                            alarmstring.append(alarmLine + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    autolight.setText("Activated");
                } else {
                    ProcessBuilder deactivate = new ProcessBuilder();
                    deactivate.command("/bin/bash", "-c", "cd Files/sketchbook/deactivate; make upload");
                    try {
                        Process deactivateprocess = deactivate.start();
                        StringBuilder deactivatestring = new StringBuilder();
                        BufferedReader deactivateread = new BufferedReader(new InputStreamReader(deactivateprocess.getInputStream()));
                        String deactivateLine;
                        while ((deactivateLine = deactivateread.readLine()) != null) {
                            deactivatestring.append(deactivateLine + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    autolight.setText("Deactivated");
                }
            }

        };
        autolight.addItemListener(light);


    }

}
