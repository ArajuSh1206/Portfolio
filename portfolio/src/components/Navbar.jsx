import React from "react";
import Logo from "../assets/AS_logo.png";
import { FaLinkedin, FaGithub, FaSquareXTwitter, FaInstagram } from "react-icons/fa6";
import { motion } from "framer-motion";

const Navbar = () => {
  return (
    <nav className="mb-20 flex items-center justify-between py-6">
      <div className="flex flex-shrink-0 items-center">
      <motion.img 
        drag 
        dragConstraints={{ left: -80, right: -10, top: -5, bottom: 10 }} // ✅ Limit drag movement
        className="mx-0 w-60 cursor-pointer" 
        src={Logo} 
        alt="Araju Sharma Logo" 
      />
      </div>
      <div className="m-8 flex items-center justify-center gap-4 text-2xl">
        <FaLinkedin />
        <FaGithub />
        <FaInstagram />
        <FaSquareXTwitter />
      </div>
    </nav>
  );
};

export default Navbar;
