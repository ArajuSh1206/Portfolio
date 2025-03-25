import React, { useState } from 'react';
import { PROJECTS } from "../constants";
import { motion } from "framer-motion";
import { FaGithub, FaExternalLinkAlt, FaTimes } from "react-icons/fa";
import Slider from "react-slick";

const Projects = () => {
  const [expandedIndex, setExpandedIndex] = useState(null);

  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: false,
  };

  const toggleExpand = (index) => {
    if (expandedIndex === index) {
      setExpandedIndex(null);
    } else {
      setExpandedIndex(index);
    }
  };

  return (
    <div className="border-b border-neutral-900 pb-4">
      <h1 className="my-20 text-center text-4xl">Projects</h1>
      <div className="flex flex-col gap-y-16 lg:gap-y-24">
        {PROJECTS.map((project, index) => (
          <div key={index} className="mb-8 flex flex-col lg:flex-row lg:justify-between lg:space-x-8 relative">
            {/* Expanded Image without Transparent Background */}
            {expandedIndex === index && (
              <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-75">
                <div className="relative max-w-5xl w-full p-6 bg-white rounded-lg shadow-xl">
                  <div className="absolute top-0 right-0 p-2 z-30">
                    <button
                      onClick={() => setExpandedIndex(null)}
                      className="text-black"
                      aria-label="Close expanded view"
                    >
                      <FaTimes size={32} />
                    </button>
                  </div>
                  {/* Carousel for Expanded Image */}
                  <Slider {...settings}>
                    {project.image.map((img, imgIndex) => (
                      <div key={imgIndex}>
                        <img
                          src={img}
                          alt={`${project.title} image ${imgIndex + 1}`}
                          className="mb-6 rounded-lg max-h-[80vh] w-auto mx-auto"
                          style={{ maxWidth: '100%', objectFit: 'contain' }}
                        />
                      </div>
                    ))}
                  </Slider>
                </div>
              </div>
            )}

            {/* Carousel for images */}
            <motion.div
              whileInView={{ opacity: 1, x: 0 }}
              initial={{ opacity: 0, x: -100 }}
              transition={{ duration: 0.5 }}
              className="w-full lg:w-1/3 relative mb-6 lg:mb-0"
            >
              <Slider {...settings}>
                {project.image.map((img, imgIndex) => (
                  <div key={imgIndex}>
                    <img
                      src={img}
                      alt={`${project.title} image ${imgIndex + 1}`}
                      className="mb-6 rounded cursor-pointer"
                      width={400}
                      height={400}
                      onClick={() => toggleExpand(index)}
                    />
                  </div>
                ))}
              </Slider>
            </motion.div>

            {/* Project Info */}
            <motion.div
              whileInView={{ opacity: 1, x: 0 }}
              initial={{ opacity: 0, x: 100 }}
              transition={{ duration: 0.5 }}
              className="w-full max-w-xl lg:w-2/3"
            >
              <div className="flex items-center justify-between mb-4">
                <h6 className="font-semibold text-xl">
                  {project.title}{" "}
                  {project.company && (
                    <span className="text-sm text-pink-400">- {project.company}</span>
                  )}
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