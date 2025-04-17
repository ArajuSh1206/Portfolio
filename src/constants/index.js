import chat1 from "../assets/projects/chatImages/chat1.png";
import chat2 from "../assets/projects/chatImages/chat2.png";
import chat3 from "../assets/projects/chatImages/chat3.png";
import chat4 from "../assets/projects/chatImages/chat4.png";
import chat5 from "../assets/projects/chatImages/chat5.png";
import chat6 from "../assets/projects/chatImages/chat6.png";
import chat7 from "../assets/projects/chatImages/chat7.png";

import blog1 from "../assets/projects/blogImages/blog1.png"
import blog2 from "../assets/projects/blogImages/blog2.png"
import blog3 from "../assets/projects/blogImages/blog3.png"
import blog4 from "../assets/projects/blogImages/blog4.png"
import blog5 from "../assets/projects/blogImages/blog5.png"
import blog6 from "../assets/projects/blogImages/blog6.png"
import blog7 from "../assets/projects/blogImages/blog7.png"
import blog8 from "../assets/projects/blogImages/blog8.png"
import blog9 from "../assets/projects/blogImages/blog9.png"
import blog10 from "../assets/projects/blogImages/blog10.png"
import blog11 from "../assets/projects/blogImages/blog11.png"

import portfolio1 from "../assets/projects/portfolioImages/portfolio1.png"
import portfolio2 from "../assets/projects/portfolioImages/portfolio2.png"

import diary1 from "../assets/projects/diaryImages/diary1.png"
import diary2 from "../assets/projects/diaryImages/diary2.png"
import diary3 from "../assets/projects/diaryImages/diary3.png"
import diary4 from "../assets/projects/diaryImages/diary4.png"
import diary5 from "../assets/projects/diaryImages/diary5.png"
import diary6 from "../assets/projects/diaryImages/diary6.png"


import "slick-carousel/slick/slick.css"; 
import "slick-carousel/slick/slick-theme.css"; 

export const HERO_CONTENT = `Hello, my name is Araju Sharma. I'm a recent grad at University of Colorado Boulder with a major in Computer Science and minor in Creative Tech and Design. I'm passionate about learning full stack development focused on creating scalable and high performance projects. With 3 years of personal hands-on experience, I specialize in front-end technologies such as HTML5, CSS, React, Next.js, Express.js, and TailwindCSS as well as back-end technologies such as Node.js, PostgreSQL, and MongoDB. I also have experience working with Prisma, Firebase, Cloudinary, and Zustand. The projects I have worked on also include real time features using Socket.io and dynamic UIs using Framer Motion.`;

export const ABOUT_TEXT = `My web development journey started with a curiosity for technology when I was very young playing around with inspect elements in Nepal. This passion grew when I moved to the U.S. and began pursuing my computer science degree at University of Colorado Boulder. I thrive in collaborative environments and enjoy tackling complex challenges to come up with high quality solutions. Outside of coding, I stay active through hiking, snowboarding, and roadtripping, and I’m passionate about contributing to open source projects whenever I can. I also like to paint and watch animes as a hobby!`;

export const EXPERIENCES = [
  {
    year: "2022 - 2023",
    role: "Quality Engineering Intern",
    company: "Tecomet",
    description: ` Participated in weekly engineering meetings, sharing ideas to improve quality and efficiency. Created presentations to highlight design updates and help spot defects early. Used Epicor ERP to track manufacturing inefficiencies and supported non-conformance tracking to make sure issues were addressed and improvements were made.`, 
    technologies: ["Quality", "Mechanical", "Manufacturing", "etq", "epicor"],
  },
  {
    year: "2021 - 2022",
    role: "Resident Advisor",
    company: "CU Boulder - Stearns East",
    description: `Held weekly floor meetings to connect with residents and share updates on upcoming CU events. Provided overnight on-call support for emergencies to ensure a safe environment. Acted as a resource for academic, mental health, and housing support. Assisted in resolving conflicts between residents and worked with campus authorities and staff to address safety concerns and improve the dorm experience.`,
    technologies: ["Leadership", "Communication", "Event Coordination", "Conflict Resolution"],
  },
  {
    year: "2019 - 2021",
    role: "Mobile Expert",
    company: "T-mobile",
    description: `Stayed up-to-date with daily promotions to inform customers and maximize sales. Consistently met store sales goals and was given recognition for being an A-list salesperson for consecutive months. Attended weekly one-on-one checkups with management to discuss performance and set targets. Helped resolve customer conflicts, ensuring positive experiences and satisfaction with purchases and devices.`,
    technologies: ["Sales", "Customer Service", "Conflict Resolution", "Product Knowledge"],
  },
];

export const PROJECTS = [
  {
    title: "GUFF Chat Application",
    image: [chat7, chat6, chat3, chat5, chat1, chat2, chat4],
    description:
      "A real-time chat app built with the MERN stack (MongoDB, Express.js, React, Node.js) and Socket.io. Users can securely log in with JWT authentication and hashed passwords. This contains live messaging, online status updates, and smooth state management with Zustand. The app features a clean, responsive design using TailwindCSS and Daisy UI. With built-in error handling and free deployment, this project highlights my ability to create secure, interactive web apps that are both functional and user-friendly.",
    technologies: ["Express.js", "React", "Node.js", "MongoDB", "Zustand", "Cloudinary", "Tailwind", "Socket.io", "onRender"],
    githubLink: "https://github.com/ArajuSh1206/Chat-app",
    liveLink: "https://guff-chat-app.onrender.com/login"
  },
  {
    title: "GUFF Blogging Platform",
    image: [blog1, blog2, blog3, blog4, blog5, blog6, blog7, blog8, blog9, blog10, blog11],
    description:
      "This Next.js 13 full-stack blogging application enables seamless authentication via Google allowing users to create and manage profiles. It features a rich text editor (Quill) for writing posts with embedded images, videos, and links. Posts can be viewed, commented on, and deleted. It also uses Prisma and MongoDB for tracking views to highlight popular content on the homepage. This project showcases my experience in authentication, database management, real-time updates, and responsive UI design.",
    technologies: ["Next.js", "React", "CSS", "MongoDB", "Prisma", "Firebase", "Socket.io", "Vercel"],
    githubLink: "https://github.com/ArajuSh1206/blog-app",
    liveLink: "https://blog-app-git-main-araju-sharmas-projects.vercel.app/"
  },
    {
    title: "MyDiary Application",
    image: [diary1, diary2, diary3, diary4, diary5, diary6],
    description:
      "This is a simple .net application I created in order to learn how to build an application using ASP.net & C# on backend. This application has a CRUD functionality, which allows user to create, read, update and delete entries. This application also utilize docker & azure sql for database. It was created for learning purposes.",
    technologies: ["C#", "ASP.net 9", "HTML", "CSS", "Javascript", "Azure", "Docker"],
    githubLink: "https://github.com/ArajuSh1206/MyDiary",
  },
  {
    title: "Portfolio Website",
    image: [portfolio1, portfolio2],
    description:
      "This React portfolio website showcases a clean, modern design with a fully responsive layout using Tailwind CSS. It includes sections for my bio, technical skills, work experience, and projects, along with a contact form. I used Framer Motion to add smooth animations, bringing elements like tech logos, job details, and project info to life. This project reflects my ability to build interactive, polished websites with a focus on user experience and seamless design.",
    technologies: ["React", "Tailwind", "Node.js", "Framer Motion"],
    githubLink: "https://github.com/ArajuSh1206/Portfolio",
    liveLink: "https://portfolio-git-main-araju-sharmas-projects.vercel.app/"
  },
];

export const CONTACT = {
  address: "Boulder, CO, 80303",
  phoneNo: "+1 720 589 3857 ",
  email: "arajusharma417@gmail.com",
};
