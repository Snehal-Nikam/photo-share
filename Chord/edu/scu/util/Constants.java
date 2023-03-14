package edu.scu.util;

import edu.scu.config.ConfigHelper;

import java.util.HashMap;

public class Constants {
    public static final int _mpiPort = 5678;
    public static final String _photoStoragePath = System.getProperty("user.home");
    public static final String _photoStoragePathB = System.getProperty("user.home")+"/backUp/";

    public static HashMap<String, Integer> _refMap;
    public static HashMap<Integer, String> _nodeIpMap;

    public  static HashMap<Integer,Integer> _backref;
    public static boolean initConfig() {
        try {
            _refMap = ConfigHelper.getReferenceMap();
            _nodeIpMap = ConfigHelper.getIpMap();
            _backref = ConfigHelper.getBackUpReferenceMap();
            if (_refMap == null || _nodeIpMap == null || _backref == null) {
                System.exit(-1);
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
