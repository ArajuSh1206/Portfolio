import React from 'react';
import { RiReactjsLine, RiHtml5Line, RiCss3Line } from "react-icons/ri";
import { SiMongodb, SiExpress, SiTailwindcss, SiFirebase } from "react-icons/si";
import { DiRedis, DiDocker, DiPython } from "react-icons/di";
import { FaNodeJs, FaJava } from "react-icons/fa";
import { BiLogoPostgresql, BiLogoNodejs } from "react-icons/bi";
import { TbBrandNextjs } from "react-icons/tb";
import { motion } from "framer-motion";
import { GiBearFace } from "react-icons/gi";

// More organic flowing animation
const iconVariants = (duration, delay) => ({
  initial: { 
    y: 0,
    opacity: 0.8,
    scale: 1
  },
  animate: {
    y: [0, -15, 0, -7, 0],
    x: [0, 5, -5, 3, 0],
    opacity: [0.8, 1, 0.9, 1, 0.8],
    scale: [1, 1.05, 1, 1.03, 1],
    transition: {
      duration: duration,
      ease: "easeInOut",
      repeat: Infinity,
      repeatType: "loop",
      delay: delay
    }
  }
});

// Subtle hover effect for each icon
const hoverEffect = {
  whileHover: { 
    scale: 1.1,
    rotate: 5,
    transition: { duration: 0.3 }
  },
  whileTap: { 
    scale: 0.95,
    rotate: -5,
    transition: { duration: 0.2 }
  }
};

// Container animation to add depth
const containerVariants = {
  initial: { opacity: 0 },
  animate: {
    opacity: 1,
    transition: {
      staggerChildren: 0.2,
      delayChildren: 0.3
    }
  }
};

const Technologies = () => {
  return (
    <div className="border-b border-neutral-300 pb-24">
      <h1 className="my-20 text-center text-4xl font-bold text-neutral-800">Technologies</h1>
      <motion.div 
        className="flex flex-wrap items-center justify-center gap-8 px-4"
        variants={containerVariants}
        initial="initial"
        animate="animate"
      >
        <motion.div 
          variants={iconVariants(6, 0)}
          {...hoverEffect}
          className="rounded-xl border-2 border-neutral-200 p-4 bg-white shadow-lg transition-all duration-300"
        >
          <RiReactjsLine className="text-6xl text-blue-500" />
        </motion.div>

        <motion.div 
          variants={iconVariants(7, 0.7)}
          {...hoverEffect}
          className="rounded-xl border-2 border-neutral-200 p-4 bg-white shadow-lg transition-all duration-300"
        >
          <TbBrandNextjs className="text-6xl text-black" />
        </motion.div>

        <motion.div 
          variants={iconVariants(5.5, 1.2)}
          {...hoverEffect}
          className="rounded-xl border-2 border-neutral-200 p-4 bg-white shadow-lg transition-all duration-300"
        >
          <RiHtml5Line className="text-6xl text-orange-600" />
        </motion.div>

        <motion.div 
          variants={iconVariants(6.5, 0.5)}
          {...hoverEffect}
          className="rounded-xl border-2 border-neutral-200 p-4 bg-white shadow-lg transition-all duration-300"
        >
          <FaNodeJs className="text-6xl text-green-600" />
        </motion.div>

        <motion.div 
          variants={iconVariants(7.5, 1.5)}
          {...hoverEffect}
          className="rounded-xl border-2 border-neutral-200 p-4 bg-white shadow-lg transition-all duration-300"
        >
          <SiExpress className="text-6xl text-gray-800" />
        </motion.div>

        <motion.div 
          variants={iconVariants(6, 0.9)}
          {...hoverEffect}
          className="rounded-xl border-2 border-neutral-200 p-4 bg-white shadow-lg transition-all duration-300"
        >
          <SiMongodb className="text-6xl text-green-500" />
        </motion.div>

        <motion.div 
          variants={iconVariants(5.5, 1.8)}
          {...hoverEffect}
          className="rounded-xl border-2 border-neutral-200 p-4 bg-white shadow-lg transition-all duration-300"
        >
          <BiLogoPostgresql className="text-6xl text-blue-700" />
        </motion.div>

        <motion.div 
          variants={iconVariants(7, 1.1)}
          {...hoverEffect}
          className="rounded-xl border-2 border-neutral-200 p-4 bg-white shadow-lg transition-all duration-300"
        >
          <DiPython className="text-6xl text-blue-600" />
        </motion.div>

        <motion.div 
          variants={iconVariants(6.5, 0.3)}
          {...hoverEffect}
          className="rounded-xl border-2 border-neutral-200 p-4 bg-white shadow-lg transition-all duration-300"
        >
          <RiCss3Line className="text-6xl text-blue-500" />
        </motion.div>

        <motion.div 
          variants={iconVariants(5.8, 1.6)}
          {...hoverEffect}
          className="rounded-xl border-2 border-neutral-200 p-4 bg-white shadow-lg transition-all duration-300"
        >
          <SiTailwindcss className="text-6xl text-cyan-500" />
        </motion.div>

        <motion.div 
          variants={iconVariants(7.2, 0.8)}
          {...hoverEffect}
          className="rounded-xl border-2 border-neutral-200 p-4 bg-white shadow-lg transition-all duration-300"
        >
          <SiFirebase className="text-6xl text-yellow-500" />
        </motion.div>
      </motion.div>
    </div>
  );
};

export default Technologies;