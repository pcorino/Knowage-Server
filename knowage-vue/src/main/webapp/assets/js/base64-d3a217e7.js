const u=typeof atob=="function",d=typeof Buffer=="function",i=typeof TextDecoder=="function"?new TextDecoder:void 0;typeof TextEncoder=="function"&&new TextEncoder;const b="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",l=[...b],f=(t=>{let e={};return t.forEach((r,a)=>e[r]=a),e})(l),y=/^(?:[A-Za-z\d+\/]{4})*?(?:[A-Za-z\d+\/]{2}(?:==)?|[A-Za-z\d+\/]{3}=?)?$/,o=String.fromCharCode.bind(String),h=typeof Uint8Array.from=="function"?Uint8Array.from.bind(Uint8Array):(t,e=r=>r)=>new Uint8Array(Array.prototype.slice.call(t,0).map(e)),A=t=>t.replace(/[^A-Za-z0-9\+\/]/g,""),C=/[\xC0-\xDF][\x80-\xBF]|[\xE0-\xEF][\x80-\xBF]{2}|[\xF0-\xF7][\x80-\xBF]{3}/g,c=t=>{switch(t.length){case 4:var e=(7&t.charCodeAt(0))<<18|(63&t.charCodeAt(1))<<12|(63&t.charCodeAt(2))<<6|63&t.charCodeAt(3),r=e-65536;return o((r>>>10)+55296)+o((r&1023)+56320);case 3:return o((15&t.charCodeAt(0))<<12|(63&t.charCodeAt(1))<<6|63&t.charCodeAt(2));default:return o((31&t.charCodeAt(0))<<6|63&t.charCodeAt(1))}},p=t=>t.replace(C,c),_=t=>{if(t=t.replace(/\s+/g,""),!y.test(t))throw new TypeError("malformed base64.");t+="==".slice(2-(t.length&3));let e,r="",a,x;for(let n=0;n<t.length;)e=f[t.charAt(n++)]<<18|f[t.charAt(n++)]<<12|(a=f[t.charAt(n++)])<<6|(x=f[t.charAt(n++)]),r+=a===64?o(e>>16&255):x===64?o(e>>16&255,e>>8&255):o(e>>16&255,e>>8&255,e&255);return r},s=u?t=>atob(A(t)):d?t=>Buffer.from(t,"base64").toString("binary"):_,g=d?t=>h(Buffer.from(t,"base64")):t=>h(s(t),e=>e.charCodeAt(0)),m=d?t=>Buffer.from(t,"base64").toString("utf8"):i?t=>i.decode(g(t)):t=>p(s(t)),B=t=>A(t.replace(/[-_]/g,e=>e=="-"?"+":"/")),F=t=>m(B(t));export{F as d};
//# sourceMappingURL=base64-d3a217e7.js.map
