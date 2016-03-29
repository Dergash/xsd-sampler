package com.github.dergash.xsdsampler;

import jlibs.xml.sax.XMLDocument;
import org.apache.xerces.xs.XSModel;
import jlibs.xml.xsd.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMResult;
import javax.xml.validation.Schema;
import java.nio.file.Path;

public class BasicSampler implements IXsdSampler {

    XSInstance xsInstance;

    public BasicSampler() {
        this.xsInstance = getDefaultXSInstance();
    }

    public Node getSample(Path schemaPath, String elementName) throws IllegalArgumentException {
        XSModel model = new XSParser().parse(schemaPath.toString());
        QName rootElement = new QName(elementName);
        DOMResult domResult = new DOMResult();
        XMLDocument document;
        try {
            document = new XMLDocument(domResult, true, 4, null);
        } catch (TransformerConfigurationException e) {
            throw new IllegalArgumentException("",e);
        }
        xsInstance.generate(model, rootElement, document);
        return (Document)document;
    }

    private XSInstance getDefaultXSInstance() {
        XSInstance result = new XSInstance();
        result.minimumElementsGenerated = 3;
        result.maximumElementsGenerated = 3;
        result.generateDefaultAttributes = true;
        result.generateOptionalAttributes = true;
        result.generateAllChoices = true;
        result.showContentModel = true;
        result.generateOptionalElements = true;
        //DefaultPrettyPrintPadding = 4;
        return result;
    }
}
