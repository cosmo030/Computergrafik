// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package cgg_tools;

public class StopWatch {
  private long mark;

  public StopWatch() {
    mark = System.currentTimeMillis();
  }

  public void stop(String name) {
    var time = System.currentTimeMillis() - mark;
    System.out.format("%s: time: %.2fs\n", name, time / 1e3);
  }
}
