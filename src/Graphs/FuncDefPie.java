package Graphs;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import TableSym.DataSym;
import TableSym.TableSym;
public class FuncDefPie {
    public ArrayList<Init> inits;
    public FuncDefPie(ArrayList<Init> inits) {
        this.inits = inits;
    }
    public void define(EnvGraph envG, TableSym tableSym) {
        for(Init init : inits) {
            if(init.name.equals("INIT_LABEL")) {
                InitLabel initLabel = ((InitLabel) init);
                initLabel.id += "PIE";
                initLabel.init(envG, tableSym);
            }
            else if(init.name.equals("INIT_AXIS")) {
                InitAxis initAxis = ((InitAxis) init);
                initAxis.id += "PIE";
                initAxis.init(envG, tableSym);
            }
        }
        if(inits.size() > 0) {
            try {
                pieChart(
                    envG.getArrDataSym("valoresPIE"),
                envG.getArrDataSym("ejexPIE"),
                envG.getDataSym("tituloPIE").dataS
                );
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void pieChart(ArrayList<DataSym> data, ArrayList<DataSym> titles, String title) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gráfico de Pie Personalizado");
            frame.setSize(600, 400);

            PieChart grafico = new PieChart(data, titles, title);
            frame.add(grafico);

            frame.setVisible(true);
        });
    }
}
