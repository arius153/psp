package com.example.testavimas1.service;

import com.audriuskumpis.service.EmailValidator;
import com.audriuskumpis.service.PasswordChecker;
import com.audriuskumpis.service.PhoneValidator;
import com.example.testavimas1.model.Vartotojas;
import com.example.testavimas1.repository.VartotojasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VartotojasService {

    @Autowired
    VartotojasRepository vartotojasRepository;

    public Iterable<Vartotojas> findAll() {
        return vartotojasRepository.findAll();
    }

    public Vartotojas findById(Long id) {
        return vartotojasRepository.findById(id).orElse(null);
    }

    public Vartotojas add(Vartotojas vartotojas) {
        return vartotojasRepository.save(vartotojas);
    }

    public void deleteById(Long id) {
        vartotojasRepository.deleteById(id);
    }

    public void delete(Vartotojas vartotojas) {
        vartotojasRepository.delete(vartotojas);
    }

    public void update(Vartotojas vartotojas) {
        vartotojasRepository.save(vartotojas);
    }

    public String validateVartotojas(Vartotojas vartotojas) {
        String errorMessage = "";
        EmailValidator emailValidator = new EmailValidator();
        PasswordChecker passwordChecker = new PasswordChecker(" !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".split(""));
        PhoneValidator phoneValidator = new PhoneValidator();
        if (!emailValidator.CheckForbiddenSymbols(vartotojas.getEmail())) {
            errorMessage += "Email contains forbbiden symbols\n";
        }

        if (!emailValidator.CheckTLD(vartotojas.getEmail())) {
            errorMessage += "Email Top Level Domain is wrong\n";
        }

        if (!emailValidator.HasAtSign(vartotojas.getEmail())) {
            errorMessage += "Email does not contain @ symbol\n";
        }
        if (!passwordChecker.CheckUppercase(vartotojas.getPassword())) {
            errorMessage += "Password does not contain uppercase letter\n";
        }
        if (!passwordChecker.CheckPasswordLength(vartotojas.getPassword(), 8)) {
            errorMessage += "Password is to short. Must atleast contain 8 symbols\n";
        }
        if (!passwordChecker.CheckSpecialSymbol(vartotojas.getPassword())) {
            errorMessage += "Password must containt atleast one of " + passwordChecker.getSpecialChars() + "\n";
        }
        if (!phoneValidator.CheckNumbers(vartotojas.getTelNr())) {
            errorMessage += "Telephone number is invalid! \n";
        }

        if (errorMessage.length() == 0) {
            return null;
        }
        return errorMessage;
    }
}
