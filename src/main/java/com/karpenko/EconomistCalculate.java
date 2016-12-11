package com.karpenko;

/**
 * Created by Олег on 01.11.2016.
 */
public class EconomistCalculate implements EconomistSattlements{
    String[] faktRabArray = {"8/3","3/8","8/4","4/8","2/9","4/7","7/4","6/5","5/6","4/6","2/1","6/3","2/8","1/10",
            "8д","4д","2д","12","11","8"};
            //в этом массиве записываем значение каждого компбобокса на форме AddForm
    String[] cbValuesOfAddForm = {
            String.valueOf(EconomistAddForm.cbMesBukvy1.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy2.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy3.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy4.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy5.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy6.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy7.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy8.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy9.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy10.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy11.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy12.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy13.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy14.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy15.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy16.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy17.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy18.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy19.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy20.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy21.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy22.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy23.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy24.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy25.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy26.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy27.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy28.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy29.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy30.getSelectedItem()),
            String.valueOf(EconomistAddForm.cbMesBukvy31.getSelectedItem())};

    //в этом массиве записываем значение каждого компбобокса на форме ChangeForm
    String[] cbValuesOfChangeForm = {
            String.valueOf(EconomistChangeForm.cbMesBukvy1.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy2.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy3.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy4.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy5.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy6.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy7.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy8.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy9.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy10.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy11.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy12.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy13.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy14.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy15.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy16.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy17.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy18.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy19.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy20.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy21.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy22.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy23.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy24.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy25.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy26.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy27.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy28.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy29.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy30.getSelectedItem()),
            String.valueOf(EconomistChangeForm.cbMesBukvy31.getSelectedItem())};
    int fakt_rab = 0,
            otp_O = 0,
            otp_reb_R = 0,
            netrud_B = 0,
            razr_zak_GD = 0,
            razr_adm_A = 0,
            uch_U = 0,
            progul_P = 0,
            komand_K = 0,
            vyh_prazd_V = 0,
            vsego_dney = 0,
            vsego_chas = 0,
            otrab_fakt = 0,
            sveth_Ur = 0,
            doplata = 0,
            noch = 0,
            vputi = 0,
            zavaht = 0,
            zakaz = 0;

    // метод для получения fakt_rab для AddForm
    public int getFakt_rabAdd() {
        for (int i = 0; i < cbValuesOfAddForm.length; i++)
            for (int j = 0; j < faktRabArray.length; j++)
                if (cbValuesOfAddForm[i] == faktRabArray[j])
                    fakt_rab++;
        return fakt_rab;
    }

    // метод для получения fakt_rab для ChangeForm
    public int getFakt_rabChange() {
        for (int i = 0; i < cbValuesOfChangeForm.length; i++)
            for (int j = 0; j < faktRabArray.length; j++)
                if (cbValuesOfChangeForm[i] == faktRabArray[j])
                    fakt_rab++;
        return fakt_rab;
    }
    ///////////////////////////////////////////////////
    // метод для получения getOtp для AddForm
    public int getOtp_OAdd(){
        for (int i = 0; i < cbValuesOfAddForm.length; i++)
            if (cbValuesOfAddForm[i] == "О")
                otp_O++;
        return otp_O;
    }

// метод для получения getOtp для ChangeForm
    public int getOtp_OChange(){
        for (int i = 0; i < cbValuesOfChangeForm.length; i++)
            if (cbValuesOfChangeForm[i] == "О")
                otp_O++;
        return otp_O;
    }
    // метод для получения getOtp_reb_R для AddForm
    @Override
    public int getOtp_reb_RAdd() {
        for (int i = 0; i < cbValuesOfAddForm.length; i++)
        if (cbValuesOfAddForm[i] == "Р")
            otp_reb_R++;
        return otp_reb_R;
    }

    // метод для получения getOtp_reb_R для ChangeForm
    @Override
    public int getOtp_reb_RChange() {
        for (int i = 0; i < cbValuesOfChangeForm.length; i++)
        if (cbValuesOfChangeForm[i] == "Р")
            otp_reb_R++;
        return otp_reb_R;
    }

    @Override
    public int getnetrud_BAdd() {
        for (int i = 0; i < cbValuesOfAddForm.length; i++)
            if (cbValuesOfAddForm[i] == "Б")
                netrud_B++;
        return netrud_B;
    }
    @Override
    public int getnetrud_BChange() {
        for (int i = 0; i < cbValuesOfChangeForm.length; i++)
        if (cbValuesOfChangeForm[i] == "Б")
            netrud_B++;
        return netrud_B;
    }

    @Override
    public int getrazr_zak_GDADdd() {
        for (int i = 0; i < cbValuesOfAddForm.length; i++)
        if (cbValuesOfAddForm[i] == "Г" || cbValuesOfAddForm[i] == "Д")
            razr_zak_GD++;
        return razr_zak_GD;
    }

    @Override
    public int getrazr_zak_GDChange() {
        for (int i = 0; i < cbValuesOfChangeForm.length; i++)
            if (cbValuesOfChangeForm[i] == "Г" || cbValuesOfChangeForm[i] == "Д")
                razr_zak_GD++;
        return razr_zak_GD;
    }

    @Override
    public int getrazr_adm_AAdd() {
        for (int i = 0; i < cbValuesOfAddForm.length; i++)
            if (cbValuesOfAddForm[i] == "А")
                razr_adm_A++;
        return razr_adm_A;
    }

    @Override
    public int getrazr_adm_AChange() {
        for (int i = 0; i < cbValuesOfChangeForm.length; i++)
            if (cbValuesOfChangeForm[i] == "А")
                razr_adm_A++;
        return razr_adm_A;
    }

    @Override
    public int getuch_UAdd() {
        for (int i = 0; i < cbValuesOfAddForm.length; i++)
            if (cbValuesOfAddForm[i] == "У")
                uch_U++;
        return uch_U;
    }

    @Override
    public int getuch_UChange() {
        for (int i = 0; i < cbValuesOfChangeForm.length; i++)
            if (cbValuesOfChangeForm[i] == "У")
                uch_U++;
        return uch_U;
    }

    @Override
    public int getprogul_PAdd() {
        for (int i = 0; i < cbValuesOfAddForm.length; i++)
            if (cbValuesOfAddForm[i] == "П")
                progul_P++;
        return progul_P;
    }

    @Override
    public int getprogul_PChange() {
        for (int i = 0; i < cbValuesOfChangeForm.length; i++)
            if (cbValuesOfChangeForm[i] == "П")
                progul_P++;
        return progul_P;
    }

    @Override
    public int getkomand_KAdd() {
        for (int i = 0; i < cbValuesOfAddForm.length; i++)
            if (cbValuesOfAddForm[i] == "К" || cbValuesOfAddForm[i] == "Кд")
                komand_K++;
        return komand_K;
    }

    @Override
    public int getkomand_KChange() {
        for (int i = 0; i < cbValuesOfChangeForm.length; i++)
            if (cbValuesOfChangeForm[i] == "К" || cbValuesOfChangeForm[i] == "Кд")
                komand_K++;
        return komand_K;
    }

    @Override
    public int getvyh_prazd_VAdd() {
        for (int i = 0; i < cbValuesOfAddForm.length; i++)
            if (cbValuesOfAddForm[i] == "В")
                vyh_prazd_V++;
        return vyh_prazd_V;
    }

    @Override
    public int getvyh_prazd_VChange() {
        for (int i = 0; i < cbValuesOfChangeForm.length; i++)
            if (cbValuesOfChangeForm[i] == "В")
                vyh_prazd_V++;
        return vyh_prazd_V;
    }

    @Override
    public int getvsego_dneyAdd() {
        return vsego_dney = (getFakt_rabAdd() + getOtp_OAdd() + getOtp_reb_RAdd() + getnetrud_BAdd()+
                getrazr_zak_GDADdd() + getrazr_adm_AAdd() + getuch_UAdd() + getprogul_PAdd() +
                getkomand_KAdd() + getvyh_prazd_VAdd())/2;
    }

    @Override
    public int getvsego_dneyChange() {
        return vsego_dney = (getFakt_rabChange() + getOtp_OChange() + getOtp_reb_RChange() + getnetrud_BChange()+
                getrazr_zak_GDChange() + getrazr_adm_AChange() + getuch_UChange() + getprogul_PChange() +
                getkomand_KChange() + getvyh_prazd_VChange())/2;
    }

    @Override
    public int getvsego_chasAdd() {
        return vsego_chas = getotrab_faktAdd() + getvputiAdd();
    }

    @Override
    public int getvsego_chasChange() {
        return vsego_chas = getotrab_faktChange() + getvputiChange();
    }

    @Override
    public int getotrab_faktAdd() {
        int eleven = 0,
                twelve = 0,
                eight = 0,
                vNAt = 0,
                tNav = 0,
                chNav = 0,
                vNach = 0,
                dvNad = 0,
                chNas = 0,
                sNach = 0,
                shNap = 0,
                pNash = 0,
                chNash = 0,
                dvNao = 0,
                shNat = 0,
                dNav = 0,
                oNad = 0;
        for (int i = 0; i < cbValuesOfAddForm.length; i++) {
            switch(cbValuesOfAddForm[i]) {
                case "11": eleven++; break;
                case "12": twelve++; break;
                case "8":  eight++; break;
                case "8/3": vNAt++; break;
                case "3/8": tNav++; break;
                case "4/8": chNav++; break;
                case "8/4": vNach++; break;
                case "2/9": dvNad++; break;
                case "4/7": chNas++; break;
                case "7/4": sNach++; break;
                case "6/5": shNap++; break;
                case "5/6": pNash++; break;
                case "4/6": chNash++; break;
                case "2/1": dvNao++; break;
                case "6/3": shNat++; break;
                case "2/8": dNav++; break;
                case "1/10": oNad++; break;
            }
        }
        return otrab_fakt = (eleven + vNAt + tNav + dvNad + chNas + sNach + shNap + pNash + oNad) * 11 +
                (twelve + chNav + vNach) * 12 + eight * 8 + (dNav + chNash) * 10 + dvNao * 3 + shNat * 9;
    }

    @Override
    public int getotrab_faktChange() {
        int eleven = 0,
                twelve = 0,
                eight = 0,
                vNAt = 0,
                tNav = 0,
                chNav = 0,
                vNach = 0,
                dvNad = 0,
                chNas = 0,
                sNach = 0,
                shNap = 0,
                pNash = 0,
                chNash = 0,
                dvNao = 0,
                shNat = 0,
                dNav = 0,
                oNad = 0;
        for (int i = 0; i < cbValuesOfChangeForm.length; i++) {
            switch(cbValuesOfChangeForm[i]) {
                case "11": eleven++; break;
                case "12": twelve++; break;
                case "8":  eight++; break;
                case "8/3": vNAt++; break;
                case "3/8": tNav++; break;
                case "4/8": chNav++; break;
                case "8/4": vNach++; break;
                case "2/9": dvNad++; break;
                case "4/7": chNas++; break;
                case "7/4": sNach++; break;
                case "6/5": shNap++; break;
                case "5/6": pNash++; break;
                case "4/6": chNash++; break;
                case "2/1": dvNao++; break;
                case "6/3": shNat++; break;
                case "2/8": dNav++; break;
                case "1/10": oNad++; break;
            }
        }
        return otrab_fakt = (eleven + vNAt + tNav + dvNad + chNas + sNach + shNap + pNash + oNad) * 11 +
                (twelve + chNav + vNach) * 12 + eight * 8 + (dNav + chNash) * 10 + dvNao * 3 + shNat * 9;
    }

    @Override
    public int getsveth_UrAdd() {
        return 0;
    }

    @Override
    public int getsveth_UrChange() {
        return 0;
    }

    @Override
    public int getdoplataAdd() {
        return 0;
    }

    @Override
    public int getdoplataChange() {
        return 0;
    }

    @Override
    public int getnochAdd() {
        int vNAt = 0,
                tNav = 0,
                chNav = 0,
                vNach = 0,
                dvNad = 0,
                chNas = 0,
                sNach = 0,
                shNap = 0,
                pNash = 0,
                chNash = 0,
                dvNao = 0,
                shNat = 0,
                dNav = 0,
                oNad = 0;
        for (int i = 0; i < cbValuesOfAddForm.length; i++) {
            switch(cbValuesOfAddForm[i]) {
                case "8/3": vNAt++; break;
                case "3/8": tNav++; break;
                case "4/8": chNav++; break;
                case "8/4": vNach++; break;
                case "2/9": dvNad++; break;
                case "4/7": chNas++; break;
                case "7/4": sNach++; break;
                case "6/5": shNap++; break;
                case "5/6": pNash++; break;
                case "4/6": chNash++; break;
                case "2/1": dvNao++; break;
                case "6/3": shNat++; break;
                case "2/8": dNav++; break;
                case "1/10": oNad++; break;
            }
        }
        return noch = (vNAt + vNach) * 8 + tNav * 3 + (chNav + chNas + chNash) * 4 + (dvNad + dvNao) * 2 +
                sNach * 7 + (shNap + shNat) * 6 + pNash * 5 + dNav * 9 + oNad * 1;
    }

    @Override
    public int getnochChange() {
        int vNAt = 0,
                tNav = 0,
                chNav = 0,
                vNach = 0,
                dvNad = 0,
                chNas = 0,
                sNach = 0,
                shNap = 0,
                pNash = 0,
                chNash = 0,
                dvNao = 0,
                shNat = 0,
                dNav = 0,
                oNad = 0;
        for (int i = 0; i < cbValuesOfChangeForm.length; i++) {
            switch(cbValuesOfChangeForm[i]) {
                case "8/3": vNAt++; break;
                case "3/8": tNav++; break;
                case "4/8": chNav++; break;
                case "8/4": vNach++; break;
                case "2/9": dvNad++; break;
                case "4/7": chNas++; break;
                case "7/4": sNach++; break;
                case "6/5": shNap++; break;
                case "5/6": pNash++; break;
                case "4/6": chNash++; break;
                case "2/1": dvNao++; break;
                case "6/3": shNat++; break;
                case "2/8": dNav++; break;
                case "1/10": oNad++; break;
            }
        }
        return noch = (vNAt + vNach) * 8 + tNav * 3 + (chNav + chNas + chNash) * 4 + (dvNad + dvNao) * 2 +
                sNach * 7 + (shNap + shNat) * 6 + pNash * 5 + dNav * 9 + oNad * 1;
    }


    @Override
    public int getvputiAdd() {
        int vD = 0,
                tD = 0,
                chD = 0,
                pD = 0,
                shD = 0,
                sD = 0,
                dvD = 0,
                oD = 0;
        for (int i = 0; i < cbValuesOfAddForm.length; i++) {
            switch(cbValuesOfAddForm[i]) {
                case "8д": vD++; break;
                case "3д": tD++; break;
                case "4д": chD++; break;
                case "5д": pD++; break;
                case "6д": shD++; break;
                case "7д": sD++; break;
                case "2д": dvD++; break;
                case "1д": oD++; break;
            }
        }
        return vputi = vD * 8 + tD * 3 + chD * 4 + pD * 5 + shD * 6 + sD * 7 + dvD * 2 + oD;
    }

    @Override
    public int getvputiChange() {
        int vD = 0,
                tD = 0,
                chD = 0,
                pD = 0,
                shD = 0,
                sD = 0,
                dvD = 0,
                oD = 0;
        for (int i = 0; i < cbValuesOfChangeForm.length; i++) {
            switch(cbValuesOfChangeForm[i]) {
                case "8д": vD++; break;
                case "3д": tD++; break;
                case "4д": chD++; break;
                case "5д": pD++; break;
                case "6д": shD++; break;
                case "7д": sD++; break;
                case "2д": dvD++; break;
                case "1д": oD++; break;
            }
        }
        return vputi = vD * 8 + tD * 3 + chD * 4 + pD * 5 + shD * 6 + sD * 7 + dvD * 2 + oD;
    }

    @Override
    public int getzavahtAdd() {
        return 0;
    }

    @Override
    public int getzavahtChange() {
        return 0;
    }

    @Override
    public int getzakazAdd() {
        return 0;
    }

    @Override
    public int getzakazChange() {
        return 0;
    }

    }