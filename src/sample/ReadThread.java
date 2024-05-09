package sample;

import javafx.application.Platform;
import server.transferList;
import util.LoginDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;
    List<Player> ap = new ArrayList<>();

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO ) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        //System.out.println(loginDTO.getList());
                        //System.out.println(loginDTO.getUserName());
                        //System.out.println(loginDTO.isStatus());
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    if(loginDTO.getPassword().equals("buyPlayer") && loginDTO.isStatus()){
                                        try {
                                            main.getNetworkUtil().write(loginDTO.getList());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    else {
                                        try {
                                            //System.out.println(loginDTO.getList());
                                            main.clubHomePage(loginDTO.getList(), loginDTO.getUserName());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                } else {
                                    try {
                                        System.out.println(loginDTO.getUserName());
                                        main.showAlert(loginDTO.getUserName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                    else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                List<Player> ap = (List<Player>) o;
                                try {
                                    main.tW(ap);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



