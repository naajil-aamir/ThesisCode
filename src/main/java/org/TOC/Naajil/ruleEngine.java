package org.TOC.Naajil;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ruleEngine {
    public void execteRule(situationModel model) throws DroolsParserException, IOException {
        PackageBuilder builder = new PackageBuilder();
        String ruleFile = "/situationModelRules.drl";
        InputStream resourceAsStream = getClass().getResourceAsStream(ruleFile);

        Reader ruleReader = new InputStreamReader(resourceAsStream);
        builder.addPackageFromDrl(ruleReader);
        org.drools.core.rule.Package rulePackage = builder.getPackage();
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage(rulePackage);

        WorkingMemory workingMemory = ruleBase.newStatefulSession();

        workingMemory.insert(model);
        workingMemory.fireAllRules();
        System.out.println("The adjusted friction values are: " + model.getFrictionCoefficient());
        System.out.println("The adjusted Sight values are: " + model.getSight());
        System.out.println("The adjusted Hearing values are: " + model.getHearing());
        System.out.println("The adjusted Haptic values are: " + model.getHaptics());
        System.out.println("The current traffic map is: ");
        model.printTrafficMap();

        
    }
}
