import React, { Component } from 'react';
import * as THREE from 'three';

class App extends Component {

  componentDidMount() {
    window.onmousemove = (event) => {
      this.mouseX = event.clientX - this.windowHalfX;
      this.mouseY = event.clientY - this.windowHalfY * 2;
    }

    window.onkeydown = (key) => {
      console.log(key.code);
      switch (key.code) {
        case 'ArrowUp':
          this.camera.position.z += 10;
          break;
        case 'ArrowDown':
          this.camera.position.z += -10;
          break;
      }
    }

    var SEPARATION = 100,
      AMOUNTX = 100,
      AMOUNTY = 70;

    this.windowHalfX = window.innerWidth / 2;
    this.windowHalfY = window.innerHeight / 2;
    var particle = 0;

    this.mouseX = 85;
    this.mouseY = -342;
    this.windowHalfX = window.innerWidth / 2;
    this.windowHalfY = window.innerHeight / 2;
    this.count = 0;

    const width = this.mount.clientWidth
    const height = this.mount.clientHeight
    //ADD SCENE
    this.scene = new THREE.Scene()
    //ADD CAMERA
    this.camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 1, 10000);
    this.camera.position.z = 1000;
    //ADD RENDERER
    this.renderer = new THREE.WebGLRenderer({ antialias: true, alpha: true });
    this.renderer.setClearColor(0x00000, 0);
    this.renderer.setSize(width, height)
    this.mount.appendChild(this.renderer.domElement)
    //ADD LIGHT 
    this.light = new THREE.AmbientLight(0x404040);
    this.scene.add(this.light);
    //ADD PARTICLES
    this.particles = new Array();

    var PI2 = Math.PI * 2;
    var material = new THREE.ParticleBasicMaterial({

      color: 0xffffff,
      program: function (context) {
        context.beginPath();
        context.arc(0, 0, .6, 0, PI2, true);
        context.fill();
      }
    });

    var i = 0;

    for (var ix = 0; ix < AMOUNTX; ix++) {

      for (var iy = 0; iy < AMOUNTY; iy++) {

        particle = this.particles[i++] = new THREE.Particle(material);
        particle.position.x = ix * SEPARATION - ((AMOUNTX * SEPARATION) / 2);
        particle.position.z = iy * SEPARATION - ((AMOUNTY * SEPARATION) / 2);
        this.scene.add(particle);

      }

    }

    this.start()
  }

  componentWillUnmount() {
    this.stop()
    this.mount.removeChild(this.renderer.domElement)
  }

  start = () => {
    if (!this.frameId) {
      this.frameId = requestAnimationFrame(this.animate)
    }
  }

  stop = () => {
    cancelAnimationFrame(this.frameId)
  }

  animate = () => {
    this.renderScene()
    this.frameId = window.requestAnimationFrame(this.animate)
  }

  renderScene = () => {
    this.camera.position.x += (this.mouseX - this.camera.position.x) * .05;
    this.camera.position.y += (-this.mouseY - this.camera.position.y) * .05;
    this.camera.lookAt(this.scene.position);

    var AMOUNTX = 100,
      AMOUNTY = 70;

    var i = 0;

    for (var ix = 0; ix < AMOUNTX; ix++) {

      for (var iy = 0; iy < AMOUNTY; iy++) {

        var particle = this.particles[i++];
        particle.position.y = (Math.sin((ix + this.count) * 0.3) * 50) + (Math.sin((iy + this.count) * 0.5) * 50);
        particle.scale.x = particle.scale.y = (Math.sin((ix + this.count) * 0.3) + 1) * 2 + (Math.sin((iy + this.count) * 0.5) + 1) * 2;
      }

    }
    this.count += .05;
    this.renderer.render(this.scene, this.camera);
  }

  render() {
    return (
      <div
        style={{ width: window.innerWidth, height: window.innerHeight }}
        ref={(mount) => { this.mount = mount }}
      />
    )
  }
}

export default App;

