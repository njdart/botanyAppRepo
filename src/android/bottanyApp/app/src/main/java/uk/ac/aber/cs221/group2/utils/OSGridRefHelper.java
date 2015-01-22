package uk.ac.aber.cs221.group2.utils;

/**
 * Created by xander on 22/01/2015.
 */
public class OSGridRefHelper {

    double lat,lng;
    String OsRef;

//    public static void main(String[] args){
//        OSGridRefHelper temp = new OSGridRefHelper(52.4153029,-4.082920);
//        System.out.print(temp.change());
//    }

    public double Marc(double bf0,double n,double phi0,double phi)
    {
        double Marc = bf0 * (((1 + n + ((5 / 4) * (n * n)) + ((5 / 4) * (n * n * n))) * (phi - phi0))
                - (((3 * n) + (3 * (n * n)) + ((21 / 8) * (n * n * n))) * (Math.sin(phi - phi0)) * (Math.cos(phi + phi0)))
                + ((((15 / 8) * (n * n)) + ((15 / 8) * (n * n * n))) * (Math.sin(2 * (phi - phi0))) * (Math.cos(2 * (phi + phi0))))
                - (((35 / 24) * (n * n * n)) * (Math.sin(3 * (phi - phi0))) * (Math.cos(3 * (phi + phi0)))));
        return(Marc);
    }

    public OSGridRefHelper(double tlat,double tlng){
        lat=tlat;
        lng=tlng;


    }

    public String change(){
        Double deg2rad = Math.PI / 180;
        Double rad2deg = 180.0 / Math.PI;
        Double phi = lat * deg2rad;      // convert latitude to radians
        Double lam = lng * deg2rad;   // convert longitude to radians
        Double a = 6377563.396;       // OSGB semi-major axis
        Double b = 6356256.91;        // OSGB semi-minor axis
        Double e0 = 400000.0;           // OSGB easting of false origin
        Double n0 = -100000.0;          // OSGB northing of false origin
        Double f0 = 0.9996012717;     // OSGB scale factor on central meridian
        Double e2 = 0.0066705397616;  // OSGB eccentricity squared
        Double lam0 = -0.034906585039886591;  // OSGB false east
        Double phi0 = 0.85521133347722145;    // OSGB false north
        Double af0 = a * f0;
        Double bf0 = b * f0;
        // easting
        Double slat2 = Math.sin(phi) * Math.sin(phi);
        Double nu = af0 / (Math.sqrt(1 - (e2 * (slat2))));
        Double rho = (nu * (1 - e2)) / (1 - (e2 * slat2));
        Double eta2 = (nu / rho) - 1;
        Double p = lam - lam0;
        Double IV = nu * Math.cos(phi);
        Double clat3 = Math.pow(Math.cos(phi),3);
        Double tlat2 = Math.tan(phi) * Math.tan(phi);
        Double V = (nu / 6) * clat3 * ((nu / rho) - tlat2);
        Double clat5 = Math.pow(Math.cos(phi), 5);
        Double tlat4 = Math.pow(Math.tan(phi), 4);
        Double VI = (nu / 120) * clat5 * ((5 - (18 * tlat2)) + tlat4 + (14 * eta2) - (58 * tlat2 * eta2));
        Double east = e0 + (p * IV) + (Math.pow(p, 3) * V) + (Math.pow(p, 5) * VI);
        // northing
        Double n = (af0 - bf0) / (af0 + bf0);
        Double M = Marc(bf0, n, phi0, phi);
        Double I = M + (n0);
        Double II = (nu / 2) * Math.sin(phi) * Math.cos(phi);
        Double III = ((nu / 24) * Math.sin(phi) * Math.pow(Math.cos(phi), 3)) * (5 - Math.pow(Math.tan(phi), 2) + (9 * eta2));
        Double IIIA = ((nu / 720) * Math.sin(phi) * clat5) * (61 - (58 * tlat2) + tlat4);
        Double north = I + ((p * p) * II) + (Math.pow(p, 4) * III) + (Math.pow(p, 6) * IIIA);
        int eastn = (int)Math.round(east);       // round to whole number
        int northn = (int)Math.round(north);     // round to whole number
        String nstr = ((Integer)northn).toString();      // convert to string
        String estr = ((Integer)eastn).toString();       // ditto

        Double eX = east / 500000;
        Double nX = north / 500000;
        Double tmp = Math.floor(eX)-5.0 * Math.floor(nX)+17.0;
        nX = 5 * (nX - Math.floor(nX));
        eX = 20 - 5.0 * Math.floor(nX) + Math.floor(5.0 * (eX - Math.floor(eX)));
        if (eX > 7.5)
            eX = eX + 1;
        if (tmp > 7.5)
            tmp = tmp + 1;
        String eing = nstr;
        String ning = estr;
        int lnth = eing.length();
        eing = eing.substring(lnth - 5, lnth);
        lnth = ning.length();
        ning = ning.substring(lnth - 5, lnth);
        String ngr = Character.charCount(tmp.intValue() + 65) + Character.charCount(eX.intValue() + 65) + " " + eing + " " + ning;
        return ngr;
    }
}
