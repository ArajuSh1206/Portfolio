import React from "react";
import Logo from "../assets/AS_logo.png";
import { FaLinkedin, FaGithub, FaSquareXTwitter, FaInstagram } from "react-icons/fa6";
import { motion } from "framer-motion";

const Navbar = () => {
  const socialLinks = [
    { icon: <FaLinkedin />, url: "https://www.linkedin.com/in/araju-sharma-15578021a/", name: "LinkedIn" },
    { icon: <FaGithub />, url: "https://github.com/ArajuSh1206", name: "GitHub" },
    { icon: <FaInstagram />, url: "https://www.instagram.com/siimransharma", name: "Instagram" },
  ];

  return (
    <nav className="mb-20 flex items-center justify-between py-6">
      <div className="flex flex-shrink-0 items-center">
        <motion.img 
          drag 
          dragConstraints={{ left: -80, right: -10, top: -5, bottom: 10 }}
          dragElastic={0.2}
          whileHover={{ scale: 1.05 }}
          whileTap={{ scale: 0.95 }}
          className="mx-0 w-60 cursor-pointer" 
          src={Logo} 
          alt="Araju Sharma Logo" 
        />
      </div>
      <div className="m-8 flex items-center justify-center gap-4 text-2xl">
        {socialLinks.map((social, index) => (
          <a 
            key={index}
            href={social.url}
            target="_blank"
            rel="noopener noreferrer"
            aria-label={`Visit ${social.name}`}
            className="text-gray-800 hover:text-blue-600 dark:text-gray-200 dark:hover:text-blue-400 transition-colors duration-300"
          >
            {social.icon}
          </a>
        ))}
      </div>
    </nav>
  );
};

export default Navbar;