package Graphs;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import TableSym.DataSym;
import TableSym.TableSym;
public class FuncDefBar {
    public ArrayList<Init> inits;
    public FuncDefBar(ArrayList<Init> inits) {
        this.inits = inits;
    }
    public void define(EnvGraph envG, TableSym tableSym) {
        for(Init init : inits) {
            if(init.name.equals("INIT_LABEL")) {
                InitLabel initLabel = (InitLabel) init;
                initLabel.id += "BAR";
                initLabel.init(envG, tableSym);
            }
            else if(init.name.equals("INIT_AXIS")) {
                InitAxis initAxis = (InitAxis) init;
                initAxis.id += "BAR";
                initAxis.init(envG, tableSym);
            }
        }
        if(inits.size() > 0) {
            try {
                barGraph(
                    envG.getArrDataSym("valoresBAR"),
                    envG.getArrDataSym("ejexBAR"),
                    envG.getDataSym("tituloBAR").dataS,
                    envG.getDataSym("tituloxBAR").dataS,
                    envG.getDataSym("tituloyBAR").dataS
                );
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void barGraph(ArrayList<DataSym> heights, ArrayList<DataSym> labels, String title, String xTitle, String yTitle) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gráfica de Barras");
            frame.setSize(600, 400);

            BarGraph grafica = new BarGraph(heights, labels, title, xTitle, yTitle);
            frame.add(grafica);

            frame.setVisible(true);
        });
    }
}
