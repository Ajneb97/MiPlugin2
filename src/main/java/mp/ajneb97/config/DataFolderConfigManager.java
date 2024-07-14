package mp.ajneb97.config;

import mp.ajneb97.MiPlugin2;

import java.io.File;
import java.util.ArrayList;

public abstract class DataFolderConfigManager {
    protected MiPlugin2 plugin;
    protected String folderName;
    protected ArrayList<CustomConfig> configFiles;

    public DataFolderConfigManager(MiPlugin2 plugin, String folderName){
        this.plugin = plugin;
        this.folderName = folderName;
        this.configFiles = new ArrayList<>();
        configure();
    }

    public void configure() {
        createFolder();
        reloadConfigs();
    }

    public void reloadConfigs(){
        this.configFiles = new ArrayList<>();
        registerConfigFiles();
        loadConfigs();
    }

    public void createFolder(){
        File folder;
        try {
            folder = new File(plugin.getDataFolder() + File.separator + folderName);
            if(!folder.exists()){
                folder.mkdirs();
            }
        } catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    public void saveConfigFiles() {
        for (CustomConfig configFile : configFiles) {
            configFile.saveConfig();
        }
    }

    public void registerConfigFiles(){
        String path = plugin.getDataFolder() + File.separator + folderName;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                registerConfigFile(file.getName());
            }
        }
    }

    public ArrayList<CustomConfig> getConfigs(){
        return this.configFiles;
    }

    public CustomConfig getConfigFile(String pathName) {
        for (CustomConfig configFile : configFiles) {
            if (configFile.getPath().equals(pathName)) {
                return configFile;
            }
        }
        return null;
    }

    public CustomConfig registerConfigFile(String pathName) {
        CustomConfig config = new CustomConfig(pathName, folderName, plugin, true);
        config.registerConfig();
        configFiles.add(config);
        return config;
    }


    public abstract void loadConfigs();

    public abstract void saveConfigs();
}
