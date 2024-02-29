// Find the latest version by visiting https://unpkg.com/three, currently it's 0.126.1

import * as THREE from 'https://unpkg.com/three@0.126.1/build/three.module.js';

import { OrbitControls } from 'https://unpkg.com/three@0.126.1/examples/jsm/controls/OrbitControls.js';
import {getFresnelMat} from "./earthJSFunctions/getFresnelMat.js";
import {getStarfield} from "./earthJSFunctions/getStarfield.js";





window.THREE = THREE;

const w = window.innerWidth;
const h = window.innerHeight;
const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(75, w / h, 0.1, 1000);
camera.position.z = 5;
const renderer = new THREE.WebGLRenderer({ antialias: true });
renderer.setSize(w, h);
document.body.appendChild(renderer.domElement);

//earthImages grouping
const earthGroup = new THREE.Group();
earthGroup.rotation.y = 23.4 + Math.PI / 180;
scene.add(earthGroup);
new OrbitControls(camera, renderer.domElement);

//geometry
const detail = 16;
const loader = new THREE.TextureLoader();
const geometry = new THREE.IcosahedronGeometry(1, detail);
const material = new THREE.MeshPhongMaterial({
    map: loader.load("./earthImages/2_no_clouds_16k.jpg"),
});
const earthMesh = new THREE.Mesh(geometry,material);
earthGroup.add(earthMesh);

//boundaries
const bondsMat = new THREE.MeshBasicMaterial({
    map: loader.load("./earthImages/boundaries_16k.png"),
    transparent: true,
    opacity: 0.4,
    blending: THREE.AdditiveBlending,
})
const bondMesh = new THREE.Mesh(geometry,bondsMat);
earthGroup.add(bondMesh);

// //Cities
// const cityMat = new THREE.MeshBasicMaterial({
//     map: loader.load("./cities.jog"),
//     blending: THREE.AdditiveBlending,
// })
// const cityMesh = new THREE.Mesh(geometry,cityMat);
// earthGroup.add(cityMesh);

//night time of earthImages
const lightsMat = new THREE.MeshBasicMaterial({
    map: loader.load("./earthImages/5_night_16k.jpg"),
    blending: THREE.AdditiveBlending,
})

const lightMesh = new THREE.Mesh(geometry,lightsMat);
earthGroup.add(lightMesh);

//clouds
const cloudsMat = new THREE.MeshStandardMaterial({
    map: loader.load("./earthImages/africa_clouds_8k.jpg"),
    
    opacity: 1,
    blending: THREE.AdditiveBlending,
})
const cloudMesh = new THREE.Mesh(geometry,cloudsMat);
cloudMesh.scale.setScalar(1.003);
earthGroup.add(cloudMesh);


// //blue sphere
// function getFresnelMat({rimHex = 0x0088ff, facingHex = 0x000000} = {}) {
//     const uniforms = {
//         color1: { value: new THREE.Color(rimHex) },
//         color2: { value: new THREE.Color(facingHex) },
//         fresnelBias: { value: 0.1 },
//         fresnelScale: { value: 1.0 },
//         fresnelPower: { value: 4.0 },
//     };
//     const vs = `
//   uniform float fresnelBias;
//   uniform float fresnelScale;
//   uniform float fresnelPower;
//
//   varying float vReflectionFactor;
//
//   void main() {
//     vec4 mvPosition = modelViewMatrix * vec4( position, 1.0 );
//     vec4 worldPosition = modelMatrix * vec4( position, 1.0 );
//
//     vec3 worldNormal = normalize( mat3( modelMatrix[0].xyz, modelMatrix[1].xyz, modelMatrix[2].xyz ) * normal );
//
//     vec3 I = worldPosition.xyz - cameraPosition;
//
//     vReflectionFactor = fresnelBias + fresnelScale * pow( 1.0 + dot( normalize( I ), worldNormal ), fresnelPower );
//
//     gl_Position = projectionMatrix * mvPosition;
//   }
//   `;
//     const fs = `
//   uniform vec3 color1;
//   uniform vec3 color2;
//
//   varying float vReflectionFactor;
//
//   void main() {
//     float f = clamp( vReflectionFactor, 0.0, 1.0 );
//     gl_FragColor = vec4(mix(color2, color1, vec3(f)), f);
//   }
//   `;
//     const fresnelMat = new THREE.ShaderMaterial({
//         uniforms: uniforms,
//         vertexShader: vs,
//         fragmentShader: fs,
//         transparent: true,
//         blending: THREE.AdditiveBlending,
//         // wireframe: true,
//     });
//     return fresnelMat;
// }
const fresnelMat = getFresnelMat();
const glowMesh = new THREE.Mesh(geometry, fresnelMat);
glowMesh.scale.setScalar(1.008);
earthGroup.add(glowMesh);

// //stars of universe
// function getStarfield({ numStars = 10000 } = {}) {
//     function randomSpherePoint() {
//         const radius = Math.random() * 75 + 25;
//         const u = Math.random();
//         const v = Math.random();
//         const theta = 2 * Math.PI * u;
//         const phi = Math.acos(2 * v - 1);
//         let x = radius * Math.sin(phi) * Math.cos(theta);
//         let y = radius * Math.sin(phi) * Math.sin(theta);
//         let z = radius * Math.cos(phi);
//
//         return {
//             pos: new THREE.Vector3(x, y, z),
//             hue: 0.6,
//             minDist: radius,
//         };
//     }
//     const verts = [];
//     const colors = [];
//     const positions = [];
//     let col;
//     for (let i = 0; i < numStars; i += 1) {
//         let p = randomSpherePoint();
//         const { pos, hue } = p;
//         positions.push(p);
//         col = new THREE.Color().setHSL(hue, 0.2, Math.random());
//         verts.push(pos.x, pos.y, pos.z);
//         colors.push(col.r, col.g, col.b);
//     }
//     const geo = new THREE.BufferGeometry();
//     geo.setAttribute("position", new THREE.Float32BufferAttribute(verts, 3));
//     geo.setAttribute("color", new THREE.Float32BufferAttribute(colors, 3));
//     const mat = new THREE.PointsMaterial({
//         size: 0.3,
//         vertexColors: true,
//         map: new THREE.TextureLoader().load(
//             "./WebView/circle.png"
//         ),
//
//     });
//     const points = new THREE.Points(geo, mat);
//     return points;
// }
const stars = getStarfield();
scene.add(stars);

//light
const sunLight = new THREE.DirectionalLight(0xffffff);
sunLight.position.set(-2, 0.5, 1.5);
scene.add(sunLight);

//footer


function animate() {
    requestAnimationFrame(animate);

    earthMesh.rotation.y += 0.0002;
    lightMesh.rotation.y += 0.0002;
    bondMesh.rotation.y += 0.0002;
    // cityMesh.rotation.y += 0.0005;
    cloudMesh.rotation.y += 0.0004;
    cloudMesh.rotation.x += 0.0001;
    cloudMesh.rotation.z += -0.00001;

    renderer.render(scene, camera);
}

animate();

function handleWindowResize () {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(window.innerWidth, window.innerHeight);
}
window.addEventListener('resize', handleWindowResize, false);