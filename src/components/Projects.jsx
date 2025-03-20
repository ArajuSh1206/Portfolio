import React from 'react';
import { PROJECTS } from "../constants";
import { motion } from "framer-motion";
import { FaGithub, FaExternalLinkAlt } from "react-icons/fa";

const Projects = () => {
  return (
    <div className="border-b border-neutral-900 pb-4">
      <h1 className="my-20 text-center text-4xl">Projects</h1>
      <div>
        {PROJECTS.map((project, index) => (
          <div key={index} className="mb-8 flex flex-wrap lg:justify-center">
            <motion.div 
              whileInView={{ opacity: 1, x: 0 }}
              initial={{ opacity: 0, x: -100 }}
              transition={{ duration: 0.5 }}
              className="w-full lg:w-1/4">
              <img
                src={project.image}
                alt={project.title}
                className="mb-6 rounded"
                width={150}
                height={150}
              />
            </motion.div>
            <motion.div 
              whileInView={{ opacity: 1, x: 0 }}
              initial={{ opacity: 0, x: 100 }}
              transition={{ duration: 0.5 }}
              className="w-full max-w-xl lg:w-3/4">
              <div className="flex items-center justify-between mb-2">
                <h6 className="font-semibold">
                  {project.title} {" "}
                  {project.company && <span className="text-sm text-pink-400">- {project.company}</span>}
                </h6>
                <div className="flex space-x-3">
                  {project.githubLink && (
                    <a 
                      href={project.githubLink} 
                      target="_blank" 
                      rel="noopener noreferrer"
                      className="text-neutral-400 hover:text-pink-400 transition-colors duration-300"
                      aria-label={`GitHub repository for ${project.title}`}
                    >
                      <FaGithub size={20} />
                    </a>
                  )}
                  {project.liveLink && (
                    <a 
                      href={project.liveLink} 
                      target="_blank" 
                      rel="noopener noreferrer"
                      className="text-neutral-400 hover:text-pink-400 transition-colors duration-300"
                      aria-label={`Live demo for ${project.title}`}
                    >
                      <FaExternalLinkAlt size={18} />
                    </a>
                  )}
                </div>
              </div>
              <p className="mb-4 text-neutral-400">{project.description}</p>
              <div className="flex flex-wrap gap-2">
                {project.technologies.map((tech, techIndex) => (
                  <span
                    key={techIndex}
                    className="rounded bg-pink-950 px-2 py-1 text-sm font-medium text-purple-200"
                  >
                    {tech}
                  </span>
                ))}
              </div>
            </motion.div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Projects;