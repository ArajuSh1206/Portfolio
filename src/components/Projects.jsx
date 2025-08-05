import React, { useState } from "react";
import { PROJECTS } from "../constants";
import { motion } from "framer-motion";
import { FaGithub, FaExternalLinkAlt, FaTimes } from "react-icons/fa";
import Slider from "react-slick";

const Projects = () => {
  const [expandedIndex, setExpandedIndex] = useState(null);
  const [expandedIsFeatured, setExpandedIsFeatured] = useState(false);

  const modalSettings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: false,
  };

  const otherProjectsSettings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 2,
    slidesToScroll: 1,
    autoplay: false,
    swipeToSlide: true,
    arrows: true,
    responsive: [
      { breakpoint: 1024, settings: { slidesToShow: 1 } },
    ],
  };

  // Separate featured and other projects
  const featuredProjects = PROJECTS.filter(
    (p) =>
      p.title === "GUFF Chat Application" || p.title === "GUFF Blogging Platform"
  );
  const otherProjects = PROJECTS.filter(
    (p) =>
      p.title !== "GUFF Chat Application" && p.title !== "GUFF Blogging Platform"
  );

  // Open modal
  const openModal = (index, isFeatured) => {
    setExpandedIndex(index);
    setExpandedIsFeatured(isFeatured);
  };

  // Close modal
  const closeModal = () => {
    setExpandedIndex(null);
  };

  return (
    <div className="border-b border-neutral-900 pb-4">
      {/* Featured Projects Section */}
      <h1 className="my-20 text-center text-4xl">Projects</h1>
      <div className="flex flex-col gap-y-16 lg:gap-y-24">
        {featuredProjects.map((project, index) => (
          <motion.div
            key={index}
            className="mb-8 flex flex-col lg:flex-row lg:justify-between lg:space-x-8 relative"
            initial={{ opacity: 0, y: 40 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            transition={{ duration: 0.6, delay: index * 0.3 }}
          >
            {/* Image */}
            <motion.div
              whileInView={{ opacity: 1, x: 0 }}
              initial={{ opacity: 0, x: -100 }}
              transition={{ duration: 0.5 }}
              className="w-full lg:w-1/3 relative mb-6 lg:mb-0 cursor-pointer"
              onClick={() => openModal(index, true)}
            >
              <img
                src={project.image[0]}
                alt={`${project.title} preview`}
                className="mb-6 rounded cursor-pointer"
                width={400}
                height={400}
              />
            </motion.div>

            {/* Info */}
            <motion.div
              whileInView={{ opacity: 1, x: 0 }}
              initial={{ opacity: 0, x: 100 }}
              transition={{ duration: 0.5 }}
              className="w-full max-w-xl lg:w-2/3"
            >
              {/* Title and Links */}
              <div className="flex items-center justify-between mb-4">
                <h6 className="font-semibold text-xl">{project.title}</h6>
                <div className="flex space-x-3">
                  {project.githubLink && (
                    <a
                      href={project.githubLink}
                      target="_blank"
                      rel="noopener noreferrer"
                      aria-label={`GitHub repository for ${project.title}`}
                      className="text-neutral-400 hover:text-pink-400 transition-colors duration-300"
                    >
                      <FaGithub size={20} />
                    </a>
                  )}
                  {project.liveLink && (
                    <a
                      href={project.liveLink}
                      target="_blank"
                      rel="noopener noreferrer"
                      aria-label={`Live demo for ${project.title}`}
                      className="text-neutral-400 hover:text-pink-400 transition-colors duration-300"
                    >
                      <FaExternalLinkAlt size={18} />
                    </a>
                  )}
                </div>
              </div>

              <p className="mb-4 text-neutral-400">{project.description}</p>
              <div className="flex flex-wrap gap-2">
                {project.technologies.map((tech, i) => (
                  <span
                    key={i}
                    className="rounded bg-pink-950 px-2 py-1 text-sm font-medium text-purple-200"
                  >
                    {tech}
                  </span>
                ))}
              </div>
            </motion.div>
          </motion.div>
        ))}
      </div>

      {/* Other Projects Section */}
      <h2 className="my-20 text-center text-4xl">Other Projects</h2>
      <div className="max-w-5xl mx-auto">
        <Slider {...otherProjectsSettings}>
          {otherProjects.map((project, index) => (
            <div
              key={index}
              className="p-4 cursor-pointer"
              onClick={() => openModal(index, false)}
            >
              <img
                src={project.image[0]}
                alt={`${project.title} preview`}
                className="rounded shadow-md object-contain max-h-48 mx-auto"
              />
              <h3 className="mt-3 text-center text-lg font-semibold text-neutral-400">{project.title}</h3>
            </div>
          ))}
        </Slider>
      </div>

      {/* Modal for Featured Projects */}
      {expandedIsFeatured && expandedIndex !== null && (
        <div
          className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-75"
          aria-modal="true"
          role="dialog"
        >
          <div className="relative max-w-5xl w-full p-6 bg-white rounded-lg shadow-xl overflow-auto max-h-[95vh]">
            <div className="absolute top-0 right-0 p-2 z-30">
              <button
                onClick={closeModal}
                className="text-black"
                aria-label="Close expanded view"
              >
                <FaTimes size={32} />
              </button>
            </div>

            <div className="flex flex-col lg:flex-row gap-10">
              <div className="lg:w-2/3">
                <Slider {...modalSettings}>
                  {featuredProjects[expandedIndex].image.map((img, imgIndex) => (
                    <div key={imgIndex}>
                      <img
                        src={img}
                        alt={`${featuredProjects[expandedIndex].title} image ${imgIndex + 1}`}
                        className="mb-6 rounded-lg max-h-[80vh] w-auto mx-auto"
                        style={{ maxWidth: '100%', objectFit: 'contain' }}
                      />
                    </div>
                  ))}
                </Slider>
              </div>

              <div className="lg:w-1/3 flex flex-col justify-center">
                {/* Title and Links */}
                <div className="flex items-center justify-between mb-6">
                  <h2 className="text-2xl font-semibold">
                    {featuredProjects[expandedIndex].title}
                  </h2>
                  <div className="flex space-x-3">
                    {featuredProjects[expandedIndex].githubLink && (
                      <a
                        href={featuredProjects[expandedIndex].githubLink}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="text-neutral-400 hover:text-pink-400 transition-colors duration-300"
                        aria-label={`GitHub repository for ${featuredProjects[expandedIndex].title}`}
                      >
                        <FaGithub size={20} />
                      </a>
                    )}
                    {featuredProjects[expandedIndex].liveLink && (
                      <a
                        href={featuredProjects[expandedIndex].liveLink}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="text-neutral-400 hover:text-pink-400 transition-colors duration-300"
                        aria-label={`Live demo for ${featuredProjects[expandedIndex].title}`}
                      >
                        <FaExternalLinkAlt size={18} />
                      </a>
                    )}
                  </div>
                </div>

                <p className="mb-6 text-neutral-800 text-lg leading-relaxed">
                  {featuredProjects[expandedIndex].description}
                </p>
                <div className="flex flex-wrap gap-2">
                  {featuredProjects[expandedIndex].technologies.map((tech, i) => (
                    <span
                      key={i}
                      className="rounded bg-pink-950 px-2 py-1 text-sm font-medium text-purple-200"
                    >
                      {tech}
                    </span>
                  ))}
                </div>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* Modal for Other Projects */}
      {!expandedIsFeatured && expandedIndex !== null && (
        <div
          className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-75"
          aria-modal="true"
          role="dialog"
        >
          <div className="relative max-w-5xl w-full p-6 bg-white rounded-lg shadow-xl overflow-auto max-h-[95vh]">
            <div className="absolute top-0 right-0 p-2 z-30">
              <button
                onClick={closeModal}
                className="text-black"
                aria-label="Close expanded view"
              >
                <FaTimes size={32} />
              </button>
            </div>

            <div className="flex flex-col lg:flex-row gap-10">
              <div className="lg:w-2/3">
                <Slider {...modalSettings}>
                  {otherProjects[expandedIndex].image.map((img, imgIndex) => (
                    <div key={imgIndex}>
                      <img
                        src={img}
                        alt={`${otherProjects[expandedIndex].title} image ${imgIndex + 1}`}
                        className="mb-6 rounded-lg max-h-[80vh] w-auto mx-auto"
                        style={{ maxWidth: '100%', objectFit: 'contain' }}
                      />
                    </div>
                  ))}
                </Slider>
              </div>

              <div className="lg:w-1/3 flex flex-col justify-center">
                {/* Title and Links */}
                <div className="flex items-center justify-between mb-6">
                  <h2 className="text-2xl font-semibold">
                    {otherProjects[expandedIndex].title}
                  </h2>
                  <div className="flex space-x-3">
                    {otherProjects[expandedIndex].githubLink && (
                      <a
                        href={otherProjects[expandedIndex].githubLink}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="text-neutral-400 hover:text-pink-400 transition-colors duration-300"
                        aria-label={`GitHub repository for ${otherProjects[expandedIndex].title}`}
                      >
                        <FaGithub size={20} />
                      </a>
                    )}
                    {otherProjects[expandedIndex].liveLink && (
                      <a
                        href={otherProjects[expandedIndex].liveLink}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="text-neutral-400 hover:text-pink-400 transition-colors duration-300"
                        aria-label={`Live demo for ${otherProjects[expandedIndex].title}`}
                      >
                        <FaExternalLinkAlt size={18} />
                      </a>
                    )}
                  </div>
                </div>

                <p className="mb-6 text-neutral-800 text-lg leading-relaxed">
                  {otherProjects[expandedIndex].description}
                </p>
                <div className="flex flex-wrap gap-2">
                  {otherProjects[expandedIndex].technologies.map((tech, i) => (
                    <span
                      key={i}
                      className="rounded bg-pink-950 px-2 py-1 text-sm font-medium text-purple-200"
                    >
                      {tech}
                    </span>
                  ))}
                </div>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Projects;