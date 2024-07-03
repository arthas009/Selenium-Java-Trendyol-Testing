package Resources.Utility;

import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

/**
 * YamlReader
 * This class is used to read configuration .yaml file
 */
public class YamlReader {
    static Map<String, Object> yamlMaps;

    /**
     * Constructor for YamlReader
     * Loads the configuration file into variable
     *
     * @param path: Path of configuration file to load
     */
    public YamlReader(String path) {
        Yaml yaml = new Yaml();
        Reader yamlFile = null;
        try {
            yamlFile = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        yamlMaps = yaml.load(yamlFile);
    }

    /**
     * getVariable
     * Gets given variable from configuration file
     *
     * @param varName variable to get
     */
    public String getString(String varName) {
        return yamlMaps.get(varName).toString();
    }

    public int getInt(String varName) {
        return (int) yamlMaps.get(varName);
    }

}
