package com.github.dergash.xsdsampler;

import org.w3c.dom.Node;

import java.nio.file.Path;

/**
 * Created by Dergachev on 3/29/2016.
 */
public interface IXsdSampler {
    public Node getSample(Path schemaPath, String elementName) ;
}
