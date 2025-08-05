import React from 'react';
import { HERO_CONTENT } from "../constants";
import profilePic from "../assets/AS6.jpg";
import { motion } from "framer-motion";

const container = (delay) => ({
  hidden : { x: -100, opacity: 0},
  visible : {
    x: 0,
    opacity: 1,
    transition: { duration: 0.5, delay: delay},
  },
});

const Hero = () => {
  return (
    <div className="border-b border-neutral-900 pb-12 lg:mb-24">
      <div className="flex flex-wrap items-center">
        {/* Text Section */}
        <div className="w-full lg:w-1/2">
          <div className="flex flex-col items-center lg:items-start text-center lg:text-left">
            <motion.h1
            variants = {container(0)}
            initial = "hidden"
            animate = "visible"
            className="pb-10 text-6xl font-light tracking-tight lg:mt-16 lg:text-8xl"
            >
              Araju Sharma
            </motion.h1>
            <motion.span
            variants = {container(0.5)}
            initial = "hidden"
            animate = "visible"
            className="bg-gradient-to-r from-pink-300 via-slate-500 to-purple-500 bg-clip-text text-3xl font-semibold tracking-tight text-transparent"
            >
              Associate Software Engineer
            </motion.span>
            <motion.p 
            variants = {container(1)}
            initial = "hidden"
            animate = "visible"
            className="my-4 max-w-xl py-6 text-lg font-light leading-relaxed tracking-tight">
              {HERO_CONTENT}
            </motion.p>
          </div>
        </div>
        <div className="w-full lg:w-1/2 lg:p-8">
          <div className="flex justify-center">
            <motion.img
              initial = {{ x: 100, opacity: 0}}
              animate = {{ x: 0, opacity: 1 }}
              transition = {{ duration:1, delay: 1.2 }}
              src={profilePic}
              alt="Araju Sharma Profile Picture"
              className="rounded-2xl w-80 h-80 md:w-85 md:h-85 lg:w-90 lg:h-90 object-cover"
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Hero;
