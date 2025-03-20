import project1 from "../assets/projects/project-1.jpg";
import project2 from "../assets/projects/project-2.jpg";
import project3 from "../assets/projects/project-3.jpg";
import project4 from "../assets/projects/project-4.jpg";

export const HERO_CONTENT = `I'm a passionate full stack developer focused on creating scalable and high performance projects. With 3 years of personal hands-on experience, I specialize in front-end technologies like HTML5, CSS, React, Next.js, Express.js, and TailwindCSS as well as back-end technologies such as Node.js, PostgreSQL, and MongoDB. I also have experience with Prisma, Firebase, Cloudinary, and Zustand for state management and database integration. My projects include real time features using Socket.io and dynamic UIs using Framer Motion and Daisy UI.`;

export const ABOUT_TEXT = `My web development journey started with a curiosity for technology, growing up in Nepal and experimenting with tools like the inspect element in my browser. This passion grew when I moved to the U.S. and began pursuing computer science at CU Boulder. I thrive in collaborative environments and enjoy tackling complex challenges to come up with high quality solutions. Outside of coding, I stay active through hiking, snowboarding, and roadtripping, and I’m passionate about contributing to open source projects whenever I can. I also like to paint and watch animes as a hobby!`;

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
    image: project2,
    description:
      "A real-time chat app built with the MERN stack (MongoDB, Express.js, React, Node.js) and Socket.io. Users can securely log in with JWT authentication and hashed passwords. This contains live messaging, online status updates, and smooth state management with Zustand. The app features a clean, responsive design using TailwindCSS and Daisy UI. With built-in error handling and free deployment, this project highlights my ability to create secure, interactive web apps that are both functional and user-friendly.",
    technologies: ["Express.js", "React", "Node.js", "MongoDB", "Zustand", "Cloudinary", "Tailwind", "Socket.io"],
    githubLink: "https://github.com/ArajuSh1206/Chat-app/tree/main/chat-app",
    liveLink: "https://guff-chat-app.vercel.app/"
  },
  {
    title: "Blogging Platform",
    image: project4,
    description:
      "This Next.js 13 full-stack blogging application enables seamless authentication via Google allowing users to create and manage profiles. It features a rich text editor (Quill) for writing posts with embedded images, videos, and links. Posts can be viewed, commented on, and deleted. It also uses Prisma and MongoDB for tracking views to highlight popular content on the homepage. This project showcases my experience in authentication, database management, real-time updates, and responsive UI design.",
    technologies: ["Next.js", "React", "CSS", "MongoDB", "Prisma", "Firebase", "Socket.io"],
    githubLink: "https://github.com/ArajuSh1206/blog-app",
    liveLink: "https://blog-platform-demo.netlify.app"
  },
  {
    title: "Portfolio Website",
    image: project3,
    description:
      "This React portfolio website showcases a clean, modern design with a fully responsive layout using Tailwind CSS. It includes sections for my bio, technical skills, work experience, and projects, along with a contact form. I used Framer Motion to add smooth animations, bringing elements like tech logos, job details, and project info to life. This project reflects my ability to build interactive, polished websites with a focus on user experience and seamless design.",
    technologies: ["React", "Tailwind", "Node.js", "Framer Motion"],
    githubLink: "https://github.com/ArajuSh1206/Portfolio/tree/main/portfolio",
    liveLink: "https://araju-sharma.com"
  },
];

export const CONTACT = {
  address: "Boulder, CO, 80303",
  phoneNo: "+1 720 589 3857 ",
  email: "arajusharma417@gmail.com",
};
