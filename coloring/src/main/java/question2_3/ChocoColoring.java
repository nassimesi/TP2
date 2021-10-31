package question2_3;

import org.apache.commons.cli.*;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.io.*;
import java.util.Scanner;

import static org.chocosolver.solver.search.strategy.Search.minDomLBSearch;
import static org.chocosolver.util.tools.ArrayUtils.append;

public class ChocoColoring {
        static int n;
        static int s;
        private static int instance;
        private static long timeout = 3600000; // one hour

        IntVar[] regions;

        Model model;
        static File fin = new File(".\\coloring\\src\\main\\resources\\datasets\\g1.col");
        static Scanner a;

    static {
        try {
            a = new Scanner(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws ParseException, FileNotFoundException {

            final Options options = configParameters();
            final CommandLineParser parser = new DefaultParser();
            final CommandLine line = parser.parse(options, args);

            boolean helpMode = line.hasOption("help"); // args.length == 0
            if (helpMode) {
                final HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("coloring", options, true);
                System.exit(0);
            }


            String firstLine = a.nextLine();
            while (!firstLine.startsWith("@")) {
                firstLine = a.nextLine();
            }
        System.out.println("hello there");
            firstLine = firstLine.substring(2,firstLine.length());
            instance = Integer.parseInt(firstLine.split(" ")[0]);
            s = Integer.parseInt(firstLine.split(" ")[1]);

            // Check arguments and options
            for (Option opt : line.getOptions()) {
                checkOption(line, opt.getLongOpt());
            }

            n = instance;
            //s = (int) Math.sqrt(n);

            new ChocoColoring().solve();
        }

        public void solve() {

            buildModel();
            model.getSolver().showStatistics();
            model.getSolver().solve();

            StringBuilder st = new StringBuilder(String.format("Coloring -- %s\n", instance, " X ", instance));
            st.append("\t");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    st.append(regions[i]).append("\t\t\t");
                }
                st.append("\n\t");
            }

            System.out.println(st.toString());
        }

        public void buildModel() {
            model = new Model();

            regions = new IntVar[n];
            for (int i = 0; i < n; i++) {
                    regions[i] = model.intVar("X_" + i , 1, s, false);
            }

            //add constraints
            while (a.hasNextLine() && a.hasNextInt()){
                int c = a.nextInt();
                int d = a.nextInt();
                //System.out.println(c +" and "+d);
                regions[c-1].ne(regions[d-1]).post();
            }




        }

        // Check all parameters values
        public static void checkOption(CommandLine line, String option) {

            switch (option) {
                case "inst":
                    instance = Integer.parseInt(line.getOptionValue(option));
                    break;
                case "timeout":
                    timeout = Long.parseLong(line.getOptionValue(option));
                    break;
                default: {
                    System.err.println("Bad parameter: " + option);
                    System.exit(2);
                }

            }

        }

        // Add options here
        private static Options configParameters() {

            final Option helpFileOption = Option.builder("h").longOpt("help").desc("Display help message").build();

            final Option instOption = Option.builder("i").longOpt("instance").hasArg(true).argName("sudoku instance")
                    .desc("sudoku instance size").required(false).build();

            final Option limitOption = Option.builder("t").longOpt("timeout").hasArg(true).argName("timeout in ms")
                    .desc("Set the timeout limit to the specified time").required(false).build();

            // Create the options list
            final Options options = new Options();
            options.addOption(instOption);
            options.addOption(limitOption);
            options.addOption(helpFileOption);

            return options;
        }

        public void configureSearch() {
            model.getSolver().setSearch(minDomLBSearch(append(regions)));

        }


    }

