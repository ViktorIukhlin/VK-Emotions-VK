let state = {
    name: '',
    description : '',
    non: false,
    include: false,
    tizer: false,
    typeOf: 'all'
}

export const updateName = (newText) => {
    state.name = newText;
}

export default state;