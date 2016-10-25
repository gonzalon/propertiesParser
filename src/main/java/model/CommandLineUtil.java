package model;

import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Util class for the command line interaction
 * @author Gonzalo Naveira
 * 
 */
public class CommandLineUtil {
	
	CommandLineParser cmdsParser;
    
	public CommandLineUtil(){
		cmdsParser = new DefaultParser();
	}
	
	/**
	 * Return the main options for the command line arguments.
	 * @return Options - The main options
	 */
	public Options getMainOptions() {
		Options mainOptions = new Options();

        Option input = Option.builder("i")
        					 .longOpt("input")
        					 .argName("Input filename")
        					 .valueSeparator('=')
        					 .desc("Input properties file.")
        					 .required(true)           
        					 .hasArg(true)
        					 .build();
        
        Option output = Option.builder("o")
							 .longOpt("output")
							 .argName("Output filename")
							 .valueSeparator('=')
							 .desc("Output file.")
							 .required(true)
							 .hasArg(true)
							 .build();
        
        Option format = Option.builder("f")
							 .longOpt("format")
							 .argName("Format")
							 .valueSeparator('=')
							 .desc("Format for the output file [json, xml].")
							 .required(true)
							 .hasArg(true)
							 .build();
        
        mainOptions.addOption(input);
        mainOptions.addOption(output);
        mainOptions.addOption(format);
        
        return mainOptions;
	}

	/**
	 * Return the help option for the command line argument.
	 * @return Options - The help option
	 */
	public Options getHelpOptions() {
		Options helpOptions = new Options();
        helpOptions.addOption( "h", "help", false, "Prints the help." );
		return helpOptions;
	}

	/**
	 * Check if the options parsed are the main ones
	 * @return boolean - True if there are the main options
	 */
	public boolean areMainOptions(String[] args) throws ParseException {
		CommandLineParser cmdsParser = new DefaultParser();
		Options helpOptions = this.getHelpOptions();
		CommandLine cmds = cmdsParser.parse(helpOptions, args, true);
		
        return (cmds.getOptions().length == 0);
	}

	/**
	 * Populate the arguments given into a hashmap.
	 * @param String[] rgs - Arguments given
	 * @return HashMap<String, String> - the mapped arguments as key, value. 
	 * @throws ParseException
	 */
	public HashMap<String, String> populateArguments(String[] args) throws ParseException {
        Options mainOptions = this.getMainOptions();
    	CommandLine cmds = this.getCmdsParser().parse(mainOptions, args);
    	HashMap<String, String> optionValue = new HashMap<String, String>();
    	
    	for(Option s : cmds.getOptions()){
    		optionValue.put(s.getLongOpt(), s.getValue());
        }
    	
    	return optionValue;
	}
	
	public CommandLineParser getCmdsParser() {
		return cmdsParser;
	}

	public void setCmdsParser(CommandLineParser cmdsParser) {
		this.cmdsParser = cmdsParser;
	}

}