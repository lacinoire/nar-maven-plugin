package com.github.maven_nar;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProjectHelper;
import org.codehaus.plexus.archiver.manager.ArchiverManager;

/**
 * Jar up the NAR files and attach them to the projects main artifact (for installation and deployment).
 * 
 * @author Mark Donszelmann
 */
@Mojo(name = "nar-package", defaultPhase = LifecyclePhase.PACKAGE, requiresProject = true)
public class NarPackageMojo
    extends AbstractNarMojo
{    
    /**
     * To look up Archiver/UnArchiver implementations
     */
    @Component(role = org.codehaus.plexus.archiver.manager.ArchiverManager.class)
    private ArchiverManager archiverManager;
    
    /**
     * Used for attaching the artifact in the project
     */
    @Component
    private MavenProjectHelper projectHelper;


    // TODO: this is working of what is present rather than what was requested to be built, POM ~/= artifacts!
    public final void narExecute()
        throws MojoExecutionException, MojoFailureException
    {
        // let the layout decide which nars to attach
        getLayout().attachNars( getTargetDirectory(), archiverManager, projectHelper, getMavenProject() );
     
    }
}
