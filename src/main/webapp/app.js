let artworks = [];
let artworksElement = document.getElementById("artworks");

function ListArtworks(artworks) {
    return `<table>
                <tr>
                    <th>Title</th>
                    <th>Price</th>
                    <th>Thumbnail</th>
                </tr>
                ${artworks.map(ListArtwork).join('')}
            </table>`;
}
{/* <td>${artwork.artid}</td> */ }
{/* <td>${artwork.approved}</td> */ }
function ListArtwork(artwork) {
    return `<tr>
                
                <td>${artwork.title}</td>
                <td>$${artwork.price}</td>
                <td><a href="/project1/assets/artwork/${artwork.filepath}" target="_blank"><img height=3% src="/project1/assets/artwork/${artwork.filepath}"></a></td>
            </tr>`;
}

function updateArtworksElement() {
    artworksElement.innerHTML = ListArtworks(artworks);
}

function getArtworks() {
    let xhr = new XMLHttpRequest();
    console.log(xhr);
    xhr.open('get', 'http://localhost:8080/project1/api/artwork/getapproved');
    xhr.onload = function () {
        artworks = JSON.parse(xhr.responseText);
        console.log(artworks);
        updateArtworksElement();
    };
    xhr.send();
}

// document.getElementById("loginForm").onsubmit(function login() {
//     localStorage.setItem("username", document.getElementById("username"));
//     sessionStorage.setItem("username", document.getElementById("username"));
// })


function login() {
    sessionStorage.setItem("username", document.getElementById('username').value);
    //sessionStorage.setItem("username", u);
    // let xhr = new XMLHttpRequest();
    // xhr.open('post', 'http://localhost:8080/project1/api/auth/');
    // xhr.onload = function () {
    //     if (xhr.response > 0) {
    //         window.location.href = '/success.html';
    //     } else {
    //         window.location.href = '/unauthorized.html';
    //     }
    // }
}
