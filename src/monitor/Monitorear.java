/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.SigarLoader;

/**
 *
 * @author morte
 */
public class Monitorear extends SigarCommandBase{
    FileSystemUsage usage = new FileSystemUsage();
    protected Sigar sigar;
    
    
    public void uso_disco(){
        sigar=new Sigar();
        System.out.println("Uso de disco: "+usage.getUsed());
        try {
            Mem mem   = this.sigar.getMem();
            System.out.println("Memoria: "+format(mem.getUsed()));
            CpuPerc[] cpus = this.sigar.getCpuPercList();
            for (int i=0; i<cpus.length; i++) {
                //println("CPU " + i + ".........");
                output(cpus[i]);
            }
            
        
        } catch (SigarException ex) {
            Logger.getLogger(Monitorear.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Long format(long value) {
        return new Long(value / 1024);
    }
    
    private void output(CpuPerc cpu) {
        println("User Time....." + CpuPerc.format(cpu.getUser()));
        println("Sys Time......" + CpuPerc.format(cpu.getSys()));
        println("Idle Time....." + CpuPerc.format(cpu.getIdle()));
        println("Wait Time....." + CpuPerc.format(cpu.getWait()));
        println("Nice Time....." + CpuPerc.format(cpu.getNice()));
        println("Combined......" + CpuPerc.format(cpu.getCombined()));
        println("Irq Time......" + CpuPerc.format(cpu.getIrq()));
        if (SigarLoader.IS_LINUX) {
            println("SoftIrq Time.." + CpuPerc.format(cpu.getSoftIrq()));
            println("Stolen Time...." + CpuPerc.format(cpu.getStolen()));
        }
        println("");
    }
    
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Monitorear monitorear = new Monitorear();
        monitorear.uso_disco();
        
        
    }

    @Override
    public void output(String[] args) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
