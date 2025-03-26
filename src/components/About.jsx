import React from 'react';
import aboutMeImg from "../assets/AS5.jpg";
import { ABOUT_TEXT } from "../constants";
import { motion } from "framer-motion";

const About = () => {
  return (
    <div className="border-b border-neutral-900 pb-12 font-sans">
      <h1 className="my-16 text-center text-4xl font-semibold">
        About <span className="text-neutral-500">Me</span>
      </h1>
      
      <div className="flex flex-wrap items-center">
        {/* Image Section */}
        <motion.div 
          whileInView = {{ opacity: 1, x: 0 }}
          initial = {{ opacity: 0, x: -100 }}
          transition = {{ duration: 0.5 }}
          className="w-full lg:w-1/2 lg:p-8">
          <div className="flex justify-center">
            <img
              src={aboutMeImg}
              alt="About Me"
              className="rounded-2xl w-80 h-80 md:w-85 md:h-85 lg:w-90 lg:h-90 object-cover"
            />
          </div>
        </motion.div>
        
        {/* Text Section */}
        <motion.div
          whileInView = {{ opacity: 1, x: 0 }}
          initial = {{ opacity: 0, x: 100 }}
          transition = {{ duration: 0.5 }}
          className="my-4 max-w-xl py-6 text-lg font-light leading-relaxed tracking-tight">
          <div className="flex justify-center lg:justify-start">
            <p className="my-2 max-w-xl py-4 text-lg leading-relaxed">
              {ABOUT_TEXT}
            </p>
          </div>
        </motion.div>
      </div>
    </div>
  );
};

export default About;
